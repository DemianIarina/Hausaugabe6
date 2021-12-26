package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import model.Pair;
import model.Teacher;
import model.Course;
import model.Student;
import repository.CourseRepository;
import repository.StudentRepository;
import repository.TeacherRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents all the actions of an university, that can be acted upon the 3 repositorys - courses, teachers and students
 */
public class RegistrationSystem {
    private CourseRepository courses;
    private StudentRepository students;
    private TeacherRepository teachers;

    public RegistrationSystem(CourseRepository courses, StudentRepository students, TeacherRepository teachers) {
        this.courses = courses;
        this.students = students;
        this.teachers = teachers;
    }

    public CourseRepository getCourses() {
        return courses;
    }

    public void setCourses(CourseRepository courses) {
        this.courses = courses;
    }

    public StudentRepository getStudents() {
        return students;
    }

    public void setStudents(StudentRepository students) {
        this.students = students;
    }

    public TeacherRepository getTeachers() {
        return teachers;
    }

    public void setTeachers(TeacherRepository teachers) {
        this.teachers = teachers;
    }

    public RegistrationSystem(){}

    /**
     * Registers a student to a specific course
     * It adds the course to the list of courses of the student in the student repository +
     * adds the student to the list of students in the course repository
     * @param course the course to which somebody wants to register
     * @param student the student who wants to register to the course
     * @return true if all repos have been updated successfully
     * @throws IllegalArgumentException if the given student or course does not exist in the according repository
     */
    public boolean register(Course course, Student student) throws IllegalArgumentException, NonexistentArgumentException, AlreadyExistingException {
        if(students.getAll().stream().noneMatch(elem -> elem.getId() == student.getId())){
            throw new NonexistentArgumentException("No such student");
        }
        else
        if(courses.getAll().stream().noneMatch(elem -> elem.getId() == course.getId())){
            throw new NonexistentArgumentException("No such course");
        }
        else {

            //update students REPO
            //update the course list of the student
            student.addCourse(course);
            students.update(student);


            //update course REPO
            course.addStudent(student.getId());
            courses.update(course);        //update the students list of the course

            return true;
        }
    }

    /**
     * Gives a list of courses which at the moment have free places - the number of enrolled students is less than the courses macEnrolled argument
     * @return a list of courses
     */
    public List<Course> retriveCoursesWithFreePlaces(){
        return courses.getAll().stream()
                .filter(elem -> elem.getMaxEnrollment()-elem.getStudentsEnrolledId().size() >0)
                .collect(Collectors.toList());
    }

    /***
     * Gives a list of all students enrolled in a given course found in the course
     * @param course the course from which we want the students enrolled
     * @return a list of students
     * @throws IllegalArgumentException if the given course does not exist is the courses repository
     */
    public List<Integer> retrieveStudentsEnrolledForACourse(Course course) throws IllegalArgumentException{
        if(courses.getAll().stream().anyMatch(elem -> elem.getId() == course.getId())){
            return course.getStudentsEnrolledId();
        }
        else
            throw new NonexistentArgumentException("No such course");
    }

    /**
     * Gives a list of all courses in the courses repository
     * @return a list of courses
     */
    public List<Course> getAllCourses(){
        return courses.getAll();
    }

    /**
     * Removes a course from the course repository, as well as from the corresponding teacher list, and every student
     * that is enrolled to it (modifying also the total credits of each student - through removeCourse)
     * @param course the course to be deleted
     * @return the modified list of courses
     * @throws IllegalArgumentException if the given course does not exist in te courses list
     */
    public List<Course> deleteCourse(Course course) throws IllegalArgumentException{
        if(courses.getAll().contains(course)){
            for (Student student: students.getAll()){       //delete from every student's list, the course
                List<Integer> enrolledCoursesId = student.getEnrolledCourses().stream()          //from the pairs, get only the id
                        .map(Pair::getCourseId)
                        .collect(Collectors.toList());

                if(enrolledCoursesId.contains(course.getId())){
                    student.removeCourse(course);

                    //update the students REPO
                    students.update(student);   //we only set the totalCredits ad delete the course from the students list
                    // from the database it is not deleted yet -> the delete course will do that
                }
            }

            //delete from the teacher REPO
            int teacherId = course.getIdTeacher();

            Teacher teacher = teachers.getAll().stream()
                    .filter(elem -> elem.getId() == teacherId)
                    .findAny()
                    .orElse(null);
            assert teacher != null;
            teacher.removeCourse(course.getId());
            teachers.update(teacher);

            //delete from the course REPO
            courses.delete(course);


            return courses.getAll();
        }
        else {
            throw new NonexistentArgumentException("No such course");
        }

    }

    /**
     * A teacher can update the number of credits of his/her course, automaticly modifying the value of the total value of credits
     * for each student enrolled by his/her course - if the total credits of the student has been reached,
     * the course will be automatically be taken out - through student.updateCourse()
     * @param course the course to be updated
     * @param newCredits the new value for numbers of credits
     * @return the updated list of courses
     */
    public List<Course> updateCreditsCourse(Course course, int newCredits){
        List<Integer> toUnenrollStudents = new ArrayList<>();

        if(courses.getAll().contains(course)) {
            //update student REPO
            for (int studentId : course.getStudentsEnrolledId()) {
                Student student = students.getAll().stream()
                        .filter(elem -> elem.getId() == studentId)
                        .findAny()
                        .orElse(null);
                try{

                    assert student != null;
                    if(student.getEnrolledCourses().stream().anyMatch(elem -> elem.getCourseId() == course.getId())) {
                        student.updateCredits(course, newCredits);
                        students.update(student);
                    }
                }
                catch (TooManyCreditsException e){
                    System.out.println("Credit limit exceded for a student:" + e);
                    int problemStudentId = e.getId();
                    toUnenrollStudents.add(problemStudentId);
                }
            }
            if(toUnenrollStudents.size()>0){
                course.getStudentsEnrolledId().removeAll(toUnenrollStudents);
                courses.update(course);
            }

            //credits number will be updated automatic in the Courses in every repo
            //update the course credits in the course REPO
            course.setCredits(newCredits);
            courses.updateCredits(course);

            return courses.getAll();
        }
        else{
            throw new NonexistentArgumentException("No such course");
        }
    }

}

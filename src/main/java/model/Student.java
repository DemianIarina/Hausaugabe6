package model;

import controller.AlreadyExistingException;
import controller.NonexistentArgumentException;
import controller.TooManyCreditsException;

import java.util.ArrayList;
import java.util.List;


/**
 * A specific type of person, enrolled in a University
 * He has a firstname, lastname, studenId, and a total number of credits from the courses he is enrolled
 * He can have maximum 30 credits
 * He can be enrolled in one or many courses
 */
public class Student extends Person implements Comparable<Student> {
    private long studentId;
    private int totalCredits;
    private List<Pair> enrolledCourses;

    public Student(int id, String firstName, String lastName, long studentId, int totalCredits) {
        super(id, firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
        this.enrolledCourses = new ArrayList<>();
    }

    public Student(int id, String firstName, String lastName, long studentId) {
        super(id, firstName, lastName);
        this.studentId = studentId;
        this.enrolledCourses = new ArrayList<>();
    }


    /**
     * Adds a new curse to the list of attended courses, and adds the corresponding number of credits to the total
     * @param course a new Course object
     * @throws TooManyCreditsException if the number of credits has been reached, the course list and credits number will not be upgraded
     * @throws AlreadyExistingException if the course already exists in the list
     */
    public void addCourse(Course course) throws TooManyCreditsException, AlreadyExistingException{
        Pair newCoursePair = new Pair(course.getId(),course.getCredits());
        if(enrolledCourses.stream().anyMatch(pair -> pair.getCourseId() == course.getId())){
            throw new AlreadyExistingException("Course already existing in the student's list");
        }

        if(totalCredits+course.getCredits() <= 30){
            enrolledCourses.add(newCoursePair);
            totalCredits = totalCredits+course.getCredits();
        }
        else {
            throw new TooManyCreditsException("The credits limit has been reached for " + this.getId(), this.getId());
        }
    }

    /**
     * Removes a course from the list of attended courses, and decreases the total number of credits, with the according value
     * @param course a course object
     * @throws NonexistentArgumentException if the course does not exist in the list
     */
    public void removeCourse(Course course) throws NonexistentArgumentException {
        if(enrolledCourses.stream().noneMatch(pair -> pair.getCourseId() == course.getId())){   //if it does not exist in the list
            throw new NonexistentArgumentException("Course does not exist in the student's list");
        }
        Pair searchedPair = enrolledCourses.stream()
                .filter(pair -> pair.getCourseId() == course.getId())
                .findAny()
                .orElse(null);

        //update the number of credits of the student
        totalCredits = totalCredits - course.getCredits();
        enrolledCourses.remove(searchedPair);
    }

    /**
     * Change the total number of credits upon the change of the number of credits of a course
     * If through the change, more than 30 credits are reached, the course will be removed from the students list, and the according value of credits will be decreased from the total
     * @param course a course object
     * @param newCredits the new value for the credits
     * @throws NonexistentArgumentException if the course does not exist in the list
     * @throws TooManyCreditsException if the maximum value of credits will be reached
     */
    public void updateCredits(Course course, int newCredits) throws NonexistentArgumentException, TooManyCreditsException{
        if(enrolledCourses.stream().noneMatch(pair -> pair.getCourseId() == course.getId())){
            throw new NonexistentArgumentException("Course does not exist in the student's list");
        }

        Pair actualCoursePair = enrolledCourses.stream()
                .filter(elem -> elem.getCourseId() == course.getId())
                .findAny()
                .orElse(null);

        //update the nr of credits of the student -> 2 possibilities (remains under 30 de credits, or not)
        if (actualCoursePair!=null) {
            int newValue = totalCredits - actualCoursePair.getCredits() + newCredits;

            if (newValue <= 30) {
                totalCredits = newValue;
                actualCoursePair.setCredits(newCredits);
            } else {
                //remove the course from the students list + throw to
                enrolledCourses.remove(actualCoursePair);
                totalCredits = totalCredits - actualCoursePair.getCredits();
                throw new TooManyCreditsException("The credits limit has been reached for " + this.studentId + ". Student unenrolled!", this.getId());
            }
        }
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Pair> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Pair> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId()  +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", studentId=" + studentId +
                ", totalCredits=" + totalCredits +
                ", enrolledCourses=" + enrolledCourses +
                '}';
    }

    /**
     * Function to compare two instances of Student, according to firstName
     * @param student2 another object of type Student
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Student student2) {
        return this.getFirstName().compareTo(student2.getFirstName());
    }


}

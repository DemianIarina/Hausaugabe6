package repository;

import model.Course;
import model.Student;
import model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherDao {
    private List<Teacher> teachers = new ArrayList<>();

    public TeacherDao(){
        Teacher teacher = new Teacher(1, "Ana", "Mare");
        teachers.add(teacher);
        Course course = new Course(1,"c1", 1, 2, 10);
        Course course2 = new Course(2,"c2", 1, 2, 10);
        teacher.addCourse(course.getId());
        teacher.addCourse(course2.getId());
        Teacher teacher2 = new Teacher(2, "Maria", "Pop");
        teachers.add(teacher2);
        Course course3 = new Course(3,"c3", 2, 2, 11);
        teacher2.addCourse(course3.getId());
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public boolean addTeacher(Teacher teacher){
        for(Teacher t: teachers){
            if(teacher.getLastName().equals(t.getLastName())){
                return false;
            }
        }
        teachers.add(teacher);
        return true;
    }
}
package repository;

import model.Course;
import model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class CourseDao {
    private List<Course> courses = new ArrayList<>();

    public CourseDao(){
        Course course = new Course(1,"c1", 1, 2, 10);
        Course course2 = new Course(2,"c2", 1, 2, 10);
        Course course3 = new Course(3,"c3", 2, 2, 11);
        course3.setStudentsEnrolledId(new ArrayList<>(List.of(101,102)));
        courses.add(course);
        courses.add(course2);
        courses.add(course3);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public boolean addCourse(Course course){
        for(Course c: courses){
            if(course.getId()==c.getId()){
                return false;
            }
        }
        courses.add(course);
        return true;
    }
}

package repository;

import model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents creating a list of courses from predefined data
 */
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

    /**
     * adds a course to the list
     * @param course the new to be added course
     * @return true if the course was succesfully added, false otherwise
     */
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

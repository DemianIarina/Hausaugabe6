package repository;

import model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents creating a list of students from predefined data
 */
public class StudentsDao {
    private List<Student> students = new ArrayList<>();

    public StudentsDao(){
        students.add(new Student(1, "Mihai", "Dinero", 101, 0));
        students.add(new Student(2, "Matei", "Rus", 102, 0));
        students.add(new Student(3, "Mihaela", "Pop", 103, 0));
    }

    public List<Student> getStudents() {
        return students;
    }

    /**
     * adds a student to the list
     * @param student the new to be added student
     * @return true if the student was succesfully added, false otherwise
     */
    public boolean addStudent(Student student){
        for(Student s: students){
            if(student.getLastName().equals(s.getLastName())){
                return false;
            }
        }
        students.add(student);
        return true;
    }

}

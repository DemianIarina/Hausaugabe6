package repository;

import model.Student;

import java.util.ArrayList;
import java.util.List;

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

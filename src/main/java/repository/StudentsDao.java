package repository;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsDao {
    private List<Student> students = new ArrayList<>();

    public StudentsDao(){
        students.add(new Student("Mihai", "Dinero", 101));
        students.add(new Student("Matei", "Rus", 102));
        students.add(new Student("Mihaela", "Pop", 103));
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

package model;

import java.util.List;

public class TeacherObserver implements IObserver {
    private TeacherSubject subject;

    public TeacherObserver(TeacherSubject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    public void update(){
        List<Student> newStudents = subject.getState();
        System.out.println(newStudents);
    }
}

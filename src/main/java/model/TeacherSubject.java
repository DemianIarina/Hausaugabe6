package model;

import java.util.ArrayList;
import java.util.List;

public class TeacherSubject{
    private List<TeacherObserver> observers;
    private List<Student> students;

    public TeacherSubject() {
        this.observers = new ArrayList<>();
        students = new ArrayList<>();
    }

    public List<Student> getState(){
        return students;
    }

    public void setState(List<Student> courses) {
        this.students = courses;
        notifyObservers();
    }

    public void attach(TeacherObserver observer){
        observers.add(observer);
    }

    public void detach(TeacherObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for(IObserver observer : observers){
            observer.update();
        }
    }
}


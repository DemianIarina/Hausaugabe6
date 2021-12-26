package model;

/**
 * Represent the general behavoiur of a Course listener, who will have
 * to do something when the list of students changes
 */
public interface CourseListener {
    void onStudentListChanged();
}

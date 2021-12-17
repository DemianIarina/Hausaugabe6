package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Course;
import model.Teacher;
import repository.CourseRepository;
import repository.StudentRepository;
import repository.TeacherRepository;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TeacherMenuController  implements Initializable {
    final StudentRepository studentRepository = new StudentRepository();
    final CourseRepository courseRepository = new CourseRepository();
    final TeacherRepository teacherRepository = new TeacherRepository();
    RegistrationSystem registrationSystem = new RegistrationSystem(courseRepository,studentRepository,teacherRepository);

    private Course teacherCourse;

    private Teacher loginedTeacher;

    @FXML
    private ImageView teacherImageView;

    @FXML
    private ImageView checkImageView;

    @FXML
    private TextArea studentsTextArea;

    @FXML
    private TextField courseNameTextField;

    @FXML
    private Label courseMessageLabel;

    @FXML
    private Label teacherNameLabel;



    public void goButtonOnAction(){
        if(!courseNameTextField.getText().isBlank()){
            validateCourse(courseNameTextField.getText());
            studentsTextArea.setText(teacherCourse.getStudentsEnrolledId().toString());
        }
        else{
            courseMessageLabel.setText("Please enter a course name");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File teacherFile = new File("images/teacher.jpg");
        Image teacherImage = new Image(teacherFile.toURI().toString());
        teacherImageView.setImage(teacherImage);

        File checkFile = new File("images/check.png");
        javafx.scene.image.Image checkImage = new Image(checkFile.toURI().toString());
        checkImageView.setImage(checkImage);

        studentsTextArea.setEditable(false);

    }

    public void validateCourse(String courseName){
        Course course = courseRepository.getAll().stream()
                .filter(elem -> Objects.equals(elem.getName(), courseName))
                .findAny()
                .orElse(null);

        if(course == null){
            courseMessageLabel.setText("No such course. Please try again.");
        }
        else {
            if(loginedTeacher.getCourses().stream().anyMatch(elem -> elem == course.getId())){
                teacherCourse = course;
                courseMessageLabel.setText("");
            }
            else{
                courseMessageLabel.setText("Course not thought by you. Please try again");
            }
        }
    }

    public void initData(String teacherName) {
        loginedTeacher = teacherRepository.getAll().stream()
                .filter(elem -> Objects.equals(elem.getFirstName(), teacherName))
                .findAny()
                .orElse(null);
        assert loginedTeacher != null;
        teacherNameLabel.setText("Hello " + teacherName + "!");
    }
}

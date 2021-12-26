package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Course;
import model.Student;
import repository.CourseRepository;
import repository.StudentRepository;
import repository.TeacherRepository;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentMenuController implements Initializable {
    StudentRepository studentRepository;
    CourseRepository courseRepository;
    TeacherRepository teacherRepository;
    RegistrationSystem registrationSystem;

    private Student loginedStudent;

    @FXML
    private Label studentNameLabel;

    @FXML
    private ImageView studentImageView;

    @FXML
    private ImageView checkImageView1;

    @FXML
    private ImageView checkImageView2;

    @FXML
    private TextField courseTextField;

    @FXML
    private Label registerMessageLabel;

    @FXML
    private TextArea creditsTextArea;


    /**
     * It triggers when the button for the registration for a course is pressed
     * and checks if the courseTextField was filled, and if not, it shows a message
     */
    public void registerButtonOnAction(){
        if(!courseTextField.getText().isBlank()){
            validateRegister();
        }
        else{
            registerMessageLabel.setText("Please enter a course name");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File studentFile = new File("images/student.png");
        Image studentImage = new Image(studentFile.toURI().toString());
        studentImageView.setImage(studentImage);

        File checkFile = new File("images/check.png");
        javafx.scene.image.Image checkImage = new Image(checkFile.toURI().toString());
        checkImageView1.setImage(checkImage);
        checkImageView2.setImage(checkImage);

        creditsTextArea.setEditable(false);

    }

    /**
     * Checks all preconditions for registering to a course:
     * if the course with the name exists, if the student is already enrolled,
     * if the credit limit has been reached, if the course has no free places
     */
    public void validateRegister(){
        Course course = courseRepository.getAll().stream()
                .filter(elem -> Objects.equals(elem.getName(), courseTextField.getText()))
                .findAny()
                .orElse(null);
        if(course == null){
            registerMessageLabel.setText("No such course. Please try again.");
        }
        else {
            try {
                registrationSystem.register(course, loginedStudent);
                registerMessageLabel.setText("Congratulation you have been enrolled!");
                creditsTextArea.setText(String.valueOf(loginedStudent.getTotalCredits()));

            } catch (AlreadyExistingException e) {
                registerMessageLabel.setText("Already enrolled to this course. \n" +
                        "Please try again.");
            }
            catch (TooManyCreditsException e2){
                registerMessageLabel.setText("Credit limits been reached. \n" +
                        "Can not enroll.");
            }
            catch (FullCourseException e3){
                registerMessageLabel.setText("Course has no free places. \n" +
                        "Can not enroll.");
            }
        }
    }


    public void initData(String studentName, RegistrationSystem registrationSystem) {
        this.registrationSystem = registrationSystem;
        studentRepository = registrationSystem.getStudents();
        courseRepository = registrationSystem.getCourses();
        teacherRepository = registrationSystem.getTeachers();

        loginedStudent = studentRepository.getAll().stream()
                .filter(elem -> Objects.equals(elem.getFirstName(), studentName))
                .findAny()
                .orElse(null);
        assert loginedStudent != null;
        creditsTextArea.setText(String.valueOf(loginedStudent.getTotalCredits()));
        studentNameLabel.setText("Hello " + studentName + "!");
    }


}

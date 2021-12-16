package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Student;
import repository.StudentRepository;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentMenuController implements Initializable {
    final StudentRepository studentRepository = new StudentRepository();
    ObservableList<Student> students = FXCollections.observableArrayList(studentRepository.getAll());

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
        creditsTextArea.setText("0");

    }

    public void validateRegister(){
        if(Objects.equals(courseTextField.getText(), "BD")){
            //todo apelat registration system
            registerMessageLabel.setText("Congratulations!");
        }
        else {
            registerMessageLabel.setText("Invalid course name. Please try again.");
        }
    }


}

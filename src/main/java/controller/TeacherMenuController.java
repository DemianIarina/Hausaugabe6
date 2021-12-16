package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TeacherMenuController  implements Initializable {

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

    public void goButtonOnAction(){
        if(!courseNameTextField.getText().isBlank()){
            validateCourse();
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

    public void validateCourse(){
        if(Objects.equals(courseNameTextField.getText(), "BD")){
            courseMessageLabel.setText("");
        }
        else {
            courseMessageLabel.setText("Invalid course name. Please try again");
        }
    }
}

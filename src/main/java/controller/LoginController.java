package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button loginStudent;

    @FXML
    private Button loginTeacher;

    @FXML
    private ImageView stududentImageView;

    @FXML
    private ImageView teacherImageView;

    public void pressLoginStudent(){
        Stage stage = (Stage) loginStudent.getScene().getWindow();
        stage.close();
    }

    public void pressLoginTeacher(){
        Stage stage = (Stage) loginTeacher.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File studentFile = new File("images/student.png");
        Image studentImage = new Image(studentFile.toURI().toString());
        stududentImageView.setImage(studentImage);

        File teacherFile = new File("images/teacher.jpg");
        Image teacherImage = new Image(teacherFile.toURI().toString());
        teacherImageView.setImage(teacherImage);
    }


}

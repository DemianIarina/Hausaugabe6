package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        try{
            URL fxmlLocation = GUI.class.getResource("studentLogin.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Stage studentLoginStage = new Stage();
            studentLoginStage.setTitle("Student login");
            studentLoginStage.setScene(new Scene(root, 601, 304));
            studentLoginStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void pressLoginTeacher(){
        try{
            URL fxmlLocation = GUI.class.getResource("teacherLogin.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Stage studentLoginStage = new Stage();
            studentLoginStage.setTitle("Teacher login");
            studentLoginStage.setScene(new Scene(root, 601, 304));
            studentLoginStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
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

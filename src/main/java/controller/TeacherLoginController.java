package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TeacherLoginController implements Initializable {
    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView teacherHeaderImageView;

    @FXML
    private ImageView lockImageView;

    public void loginButtonOnAction(){
        if(!nameTextField.getText().isBlank()){
            validateLogin();
        }
        else{
            loginMessageLabel.setText("Please enter your name");
        }
    }

    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File teachersHeaderFile = new File("images/teachers header2.png");
        javafx.scene.image.Image teachersHeaderImage = new Image(teachersHeaderFile.toURI().toString());
        teacherHeaderImageView.setImage(teachersHeaderImage);

        File lockFile = new File("images/lock.png");
        javafx.scene.image.Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }

    public void validateLogin(){
        if(Objects.equals(nameTextField.getText(), "Maria")){
            loginMessageLabel.setText("Congratulations!");
            createTeacherMenuWindow();
        }
        else {
            loginMessageLabel.setText("Invalid login. Please try again.");
        }
    }

    public void createTeacherMenuWindow(){
        try{
            URL fxmlLocation = GUI.class.getResource("teacherMenu.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Stage teacherLoginStage = new Stage();
            teacherLoginStage.setTitle("Teacher menu");
            teacherLoginStage.setScene(new Scene(root, 600, 400));
            teacherLoginStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}

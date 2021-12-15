package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import repository.StudentRepository;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentLoginController implements Initializable{
    @FXML
    private RegistrationSystem registrationSystem;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView studentHeaderImageView;

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

        File studentsHeaderFile = new File("images/students header.jpg");
        javafx.scene.image.Image studentsHeaderImage = new Image(studentsHeaderFile.toURI().toString());
        studentHeaderImageView.setImage(studentsHeaderImage);

        File lockFile = new File("images/lock.png");
        javafx.scene.image.Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }

    public void validateLogin(){

    }
}

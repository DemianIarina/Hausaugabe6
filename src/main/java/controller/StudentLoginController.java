package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Student;
import repository.StudentRepository;


import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentLoginController implements Initializable{
    final StudentRepository studentRepository = new StudentRepository();
    ObservableList<Student> students = FXCollections.observableArrayList(studentRepository.getAll());

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

        File studentsHeaderFile = new File("images/students header2.jpg");
        javafx.scene.image.Image studentsHeaderImage = new Image(studentsHeaderFile.toURI().toString());
        studentHeaderImageView.setImage(studentsHeaderImage);

        File lockFile = new File("images/lock.png");
        javafx.scene.image.Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }

    public void validateLogin(){
        if(studentRepository.getAll().stream().anyMatch(elem -> Objects.equals(elem.getFirstName(), nameTextField.getText()))){
            loginMessageLabel.setText("Congratulations!");
            createStudentMenuWindow(nameTextField.getText());
        }
        else {
            loginMessageLabel.setText("Invalid login. Please try again.");
        }
    }

    public void createStudentMenuWindow(String studentName){
        try{
            URL fxmlLocation = GUI.class.getResource("studentMenu.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Stage teacherLoginStage = new Stage();
            teacherLoginStage.setTitle("Student menu");
            teacherLoginStage.setScene(new Scene(root, 554, 414));

            StudentMenuController controller = loader.getController();
            controller.initData(studentName);

            teacherLoginStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}

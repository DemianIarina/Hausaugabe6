package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.CourseRepository;
import repository.StudentRepository;
import repository.TeacherRepository;

import java.io.IOException;
import java.net.URL;


public class GUI extends Application {
    final StudentRepository studentRepository = new StudentRepository();
    final CourseRepository courseRepository = new CourseRepository();
    final TeacherRepository teacherRepository = new TeacherRepository();
    RegistrationSystem registrationSystem = new RegistrationSystem(courseRepository,studentRepository,teacherRepository);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        URL fxmlLocation = GUI.class.getResource("loginWindow.fxml");

        FXMLLoader loader = new FXMLLoader(fxmlLocation);

        Parent root = loader.load();
        primaryStage.setTitle("Login As");
        primaryStage.setScene(new Scene(root, 601, 304));

        LoginController controller = loader.getController();
        controller.initData(registrationSystem);

        primaryStage.show();
    }
}


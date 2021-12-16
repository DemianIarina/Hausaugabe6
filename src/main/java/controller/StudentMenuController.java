package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentMenuController implements Initializable {
    @FXML
    private ImageView studentImageView;

    @FXML
    private ImageView checkImageView1;

    @FXML
    private ImageView checkImageView2;

    @FXML
    private ImageView refreshImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File studentFile = new File("images/student.png");
        Image studentImage = new Image(studentFile.toURI().toString());
        studentImageView.setImage(studentImage);

        File checkFile = new File("images/check.png");
        javafx.scene.image.Image checkImage = new Image(checkFile.toURI().toString());
        checkImageView1.setImage(checkImage);
        checkImageView2.setImage(checkImage);

        File refreshFile = new File("images/refresh.png");
        Image refreshImage = new Image(refreshFile.toURI().toString());
        refreshImageView.setImage(refreshImage);
    }



}

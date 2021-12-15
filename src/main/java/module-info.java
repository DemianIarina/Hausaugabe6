module lab7_new_new{
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.desktop;
    requires java.sql;

    opens controller;
    opens repository;
    opens model;
}
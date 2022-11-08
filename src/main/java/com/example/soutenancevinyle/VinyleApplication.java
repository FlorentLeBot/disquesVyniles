package com.example.soutenancevinyle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import static org.apache.commons.lang3.exception.ExceptionUtils.getMessage;

public class VinyleApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(VinyleApplication.class.getResource("vinyle-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Scraping");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch();
        DBController dbc = new DBController();

        //dbc.dbConnTest();
    }
}
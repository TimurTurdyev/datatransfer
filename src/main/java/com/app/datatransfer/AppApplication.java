package com.app.datatransfer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppApplication extends Application {

    private static Stage primaryStage;

    public static Stage getStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(AppApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Waviot Data Transfer Login");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(String $fileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppApplication.class.getResource($fileName));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
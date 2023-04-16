package com.example.gamecaro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GiaoDienKhoiDong.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 788, 484);
        stage.setTitle("Cờ Caro");
        stage.setScene(scene);
        stage.show();
        Sound sound = new Sound();
        sound.onBackgroundMusic(); //Bật âm thanh nền khi khởi động
    }

    public static void main(String[] args) {
        launch();
    }
}
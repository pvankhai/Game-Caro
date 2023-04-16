package com.example.gamecaro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlHuongDan {

    //Trở về giao diện khởi động
    @FXML
    private void troVe(ActionEvent event) {
        try {
            Sound sound = new Sound();
            sound.soundClick(); //Âm thanh click Button
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage stageDel = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GiaoDienKhoiDong.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 788, 484);
            stageDel.setTitle("Cờ Caro");
            stageDel.setScene(scene);
            stageDel.show();
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
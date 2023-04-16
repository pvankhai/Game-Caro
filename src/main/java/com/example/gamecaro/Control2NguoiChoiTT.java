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

public class Control2NguoiChoiTT {

    //Trở về giao diện trực tuyến
    @FXML
    private void gdTrucTuyen(ActionEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage stageDel = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GiaoDienTrucTuyen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stageDel.setTitle("Trực tuyến");
            stageDel.setScene(scene);
            stageDel.show();
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
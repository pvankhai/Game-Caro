package com.example.gamecaro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    //Bật âm thanh nền
    @FXML
    private void onSound(ActionEvent event) {
        Sound sound = new Sound();
        sound.offBackgroundMusic();
        sound.onBackgroundMusic();
        btn2.setStyle("-fx-background-color:  #FFF8DC; -fx-background-radius:  20,20");
        btn1.setStyle("-fx-background-color:  Transparent; -fx-background-radius:  20,20");
    }

    //Tắt âm thanh nền
    @FXML
    private void offSound(ActionEvent event) {
        Sound sound = new Sound();
        sound.offBackgroundMusic();
        btn1.setStyle("-fx-background-color:  #FFF8DC; -fx-background-radius:  20,20");
        btn2.setStyle("-fx-background-color:  Transparent; -fx-background-radius:  20,20");
    }



    //Chuyển sang giao diện 1 người chơi
    @FXML
    private void gd1NguoiChoi(ActionEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage stageDel = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GiaoDien1NguoiChoi.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 788, 484);
            stageDel.setTitle("Chơi với máy");
            stageDel.setScene(scene);
            stageDel.show();
            Sound sound = new Sound();
            sound.soundClick(); //Âm thanh click Button
            sound.offBackgroundMusic();
            sound.onBackgroundMusic();
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }



    //Chuyển sang giao diện 2 người chơi
    @FXML
    private void gd2NguoiChoi(ActionEvent event) {
        try {

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage stageDel = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GiaoDien2NguoiChoi.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 788, 484);
            stageDel.setTitle("Chơi với người");
            stageDel.setScene(scene);
            stageDel.show();
            Sound sound = new Sound();
            sound.soundClick(); //Âm thanh click Button
            sound.offBackgroundMusic();
            sound.onBackgroundMusic();
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //Chuyển sang giao diện trực tuyến
    @FXML
    private void gdTrucTuyen(ActionEvent event) {
        try {
            Sound sound = new Sound();
            sound.soundClick(); //Âm thanh click Button
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage stageDel = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GiaoDienTrucTuyen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 788, 484);
            stageDel.setTitle("Trực tuyến");
            stageDel.setScene(scene);
            stageDel.show();
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //Chuyển sang giao diện hướng dẫn
    @FXML
    private void gdHuongDan(ActionEvent event) {
        try {
            Sound sound = new Sound();
            sound.soundClick(); //Âm thanh click Button
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage stageDel = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GiaoDienHuongDan.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 788, 484);
            stageDel.setTitle("Hướng dẫn");
            stageDel.setScene(scene);
            stageDel.show();
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    //Xử lý nút Thoát
    @FXML
    private void thoatTroChoi(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Sound sound = new Sound();
        sound.soundClick(); //Âm thanh click Button
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thoát trò chơi");
        alert.setContentText("Bạn có chắc muốn thoát trò chơi ?");
        alert.setHeaderText(null);
        alert.getDialogPane().setStyle("-fx-background-color: #FFF8DC;-fx-border-color: #B8860B; -fx-font-size: 14px;");
        alert.getDialogPane().getStyleClass().add("dialog");
        alert.getDialogPane().lookupButton(ButtonType.OK).setStyle("-fx-font-family: Unispace; -fx-background-color:  #DAA520; -fx-font-color: #c2d453;");
        alert.getDialogPane().lookupButton(ButtonType.CANCEL).setStyle("-fx-font-family: Unispace; -fx-background-color:  #DAA520; -fx-font-color: #c2d453;");
        Optional<ButtonType> option = alert.showAndWait();
        if ( option.get()==ButtonType.OK){
            sound.soundClick();
            stage.close();
        }
        if ( option.get()==ButtonType.CANCEL){
            sound.soundClick();
        }
    }
}
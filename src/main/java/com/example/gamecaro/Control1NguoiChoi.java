package com.example.gamecaro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Control1NguoiChoi implements Initializable {

    //Bắt id từ FXML
    @FXML
    public StackPane stackPane;

    @FXML
    public Pane pane1;

    @FXML
    public Pane pane2;

    @FXML
    public Label score1;

    @FXML
    public Label score2;


    @FXML
    public Button btn1;

    @FXML
    public Button btn2;


    //Nút bật âm thanh nền
    @FXML
    private void onSound(ActionEvent event) {
        Sound sound = new Sound();
        sound.offBackgroundMusic();
        sound.onBackgroundMusic();
        btn2.setStyle("-fx-background-color:  #FFF8DC; -fx-background-radius:  20,20");
        btn1.setStyle("-fx-background-color:  Transparent; -fx-background-radius:  20,20");
    }


    //Nút tắt âm thanh nền
    @FXML
    private void offSound(ActionEvent event) {
        Sound sound = new Sound();
        sound.offBackgroundMusic();
        btn1.setStyle("-fx-background-color:  #FFF8DC; -fx-background-radius:  20,20");
        btn2.setStyle("-fx-background-color:  Transparent; -fx-background-radius:  20,20");
    }


    //Xử lý nút Bắt đầu
    @FXML
    private void batDau(ActionEvent event) {
        BanCo banCo = new BanCo();
        Sound sound = new Sound();
        sound.soundClick();
        banCo.themBanCo(stackPane);
        banCo.thongBaoMay();
        banCo.danhCoMay(stackPane, score2);

    }

    //Xử lý nút Ván mới
    @FXML
    private void vanMoi(ActionEvent event) {
        BanCo banCo = new BanCo();
        Sound sound = new Sound();
        sound.soundClick();
        banCo.themBanCo(stackPane);
        banCo.thongBaoMay();
        banCo.resetDiemMay(score2);
        banCo.danhCoMay(stackPane, score2);

    }


    //Xử lý nút Trở về
    @FXML
    private void troVe(ActionEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage stageDel = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GiaoDienKhoiDong.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 788, 484);
            stageDel.setTitle("Cờ Caro");
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
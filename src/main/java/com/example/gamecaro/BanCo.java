package com.example.gamecaro;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.kordamp.bootstrapfx.scene.layout.Panel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Optional;

public class BanCo extends Panel {

    //Khai báo
    private static final int N= 10; //Số cột
    private static final int M= 10; //Số hàng
    public int scoreX = 0;
    public int scoreO = 0;
    public int i1 = 0;
    public int j1 = 0;
    private Alert alert;
    private DialogPane dialog;
    private int i;
    private int j;
    Button b[][] = new Button[N][M]; //ô cờ
    Integer n[][] = new  Integer[N][M]; //ô rỗng


//---//  Khởi tạo trò chơi

    //Tạo bàn cờ mới 10x10
    public void themBanCo(StackPane stackPane) {
        VBox vBox = new VBox();
        HBox[] arrHBox = new HBox[N];
        for (i = 0; i < N; i++) {
            arrHBox[i] = new HBox();
            for (j = 0; j < M; j++) {
                b[i][j] = new Button();
                b[i][j].setMinSize(40, 40);
                b[i][j].setStyle("-fx-background-color: #FFF8DC;-fx-border-color: #B8860B; -fx-border-width: 0.5px;");
                arrHBox[i].getChildren().add(b[i][j]);
            }
            vBox.getChildren().add(arrHBox[i]);
        }
        stackPane.getChildren().add(vBox);
    }


//---//  Các thông báo trong trò chơi

  // Thông báo bắt đầu trò chơi
    // Thông báo người đánh trước Người - Máy
    public void thongBaoMay(){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bạn đi trước");
        alert.setContentText("Mời bạn đi trước");
        alert.setHeaderText(null);
        dialog = alert.getDialogPane();
        dialog.setStyle("-fx-background-color: #FFF8DC;-fx-border-color: #B8860B; -fx-font-size: 14px;");
        dialog.getStyleClass().add("dialog");
        dialog.lookupButton(ButtonType.OK).setStyle("-fx-font-family: Unispace; -fx-background-color:  #DAA520; -fx-font-color: #c2d453;");
        Optional<ButtonType> option = alert.showAndWait();
        if ( option.get()==ButtonType.OK){
        }
    }

    //Thông báo người đánh trước Người - Người
    public void thongBao(){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo đánh cờ");
        alert.setContentText("Mời bạn đánh cờ");
        alert.setHeaderText(null);
        dialog = alert.getDialogPane();
        dialog.setStyle("-fx-background-color: #FFF8DC;-fx-border-color: #B8860B; -fx-font-size: 14px;");
        dialog.getStyleClass().add("dialog");
        dialog.lookupButton(ButtonType.OK).setStyle("-fx-font-family: Unispace; -fx-background-color:  #DAA520; -fx-font-color: #c2d453;");
        Optional<ButtonType> option = alert.showAndWait();
        if ( option.get()==ButtonType.OK){
            Sound sound = new Sound();
            sound.soundClick();
        }
        if (option.get()==ButtonType.CLOSE){

        }

    }


  // Thông báo kết thúc trò chơi (Kết quả ván cờ)


//---//  Xử lý trò chơi
  // Xử lý 1 người chơi (Đánh với máy)
  // Xử lý 2 người chơi (Đánh với người trên cùng máy)
    //Xử lý đánh cờ (Người - Người)
    public void danhCo(StackPane stackPane, Pane pane1 , Pane pane2, Label score1, Label score2){
        pane1.setStyle("-fx-border-color: #8B4513; -fx-background-color: #FF6347; -fx-background-radius: 2,2; -fx-border-radius: 2,2;");
        pane2.setStyle("-fx-border-color: #8B4513; -fx-background-color: #FFFFE0; -fx-background-radius: 2,2; -fx-border-radius: 2,2;");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                n[i][j] = 0; //Mảng xác định ô đã đánh 0: rỗng || 1: Khác rỗng
                int finalI = i;
                int finalJ = j;
                this.b[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    public static int k = 0;
                    @Override
                    public void handle(ActionEvent e) {
                        if (n[finalI][finalJ] == 0) {
                            switch (k) {
                                case 0:
                                    BanCo.this.b[finalI][finalJ].setText("X");
                                    BanCo.this.b[finalI][finalJ].setTextFill(Color.RED);
                                    BanCo.this.b[finalI][finalJ].setFont(Font.font(18));
                                    BanCo.this.b[finalI][finalJ].setStyle("-fx-background-color:  #FFF8DC; -fx-border-color: #B8860B; -fx-border-width: 0.5px;");
                                    Sound sound = new Sound();
                                    sound.soundPlayChess();
                                    k = 1;
                                    n[finalI][finalJ] = 1;
                                        if (ktraThang(finalI, finalJ)) {
                                            thongBaoChienThang1(score1);
                                            themBanCo(stackPane);
                                            danhCo(stackPane, pane1, pane2, score1, score2);
                                            k = 1;
                                    }
                                    pane1.setStyle("-fx-border-color: #8B4513; -fx-background-color: #FFFFE0; -fx-background-radius: 2,2; -fx-border-radius: 2,2;");
                                    pane2.setStyle("-fx-border-color: #8B4513; -fx-background-color: #FF6347; -fx-background-radius: 2,2; -fx-border-radius: 2,2;");
                                    break;
                                default:
                                    BanCo.this.b[finalI][finalJ].setText("O");
                                    BanCo.this.b[finalI][finalJ].setTextFill(Color.BLUE);
                                    BanCo.this.b[finalI][finalJ].setFont(Font.font(18));
                                    BanCo.this.b[finalI][finalJ].setStyle("-fx-background-color:  #FFF8DC; -fx-border-color: #B8860B; -fx-border-width: 0.5px;");
                                    Sound sound2 = new Sound();
                                    sound2.soundPlayChess();
                                    k = 0;
                                    n[finalI][finalJ] = 1;
                                    if (ktraThang(finalI, finalJ)) {
                                        thongBaoChienThang2(score2);
                                        themBanCo(stackPane);
                                        danhCo(stackPane, pane1, pane2, score1, score2);
                                        k = 0;
                                    }
                                    pane2.setStyle("-fx-border-color: #8B4513; -fx-background-color: #FFFFE0; -fx-background-radius: 2,2; -fx-border-radius: 2,2;");
                                    pane1.setStyle("-fx-border-color: #8B4513; -fx-background-color: #FF6347; -fx-background-radius: 2,2; -fx-border-radius: 2,2;");
                                    break;
                            }
                        }
                    }
                });
            }
        }
    }


    //Kiểm tra chiến thắng (Trên 4 quân cờ cùng loại trên bàn cờ)
    public boolean ktraThang(int i, int j) {
        int d = 0, k = i, h;
        // kiểm tra hàng
        while (k < 10 && b[k][j].getText() == b[i][j].getText()) {
            d++;
            k++;
        }
        k = i - 1;
        while (k >= 0 && b[k][j].getText() == b[i][j].getText()) {
            d++;
            k--;
        }
        if (d > 4) return true;
        d = 0; h = j;
        // kiểm tra cột
        while (h < 10 && b[i][h].getText() == b[i][j].getText()) {
            d++;
            h++;
        }
        h = j - 1;
        while (h >= 0 && b[i][h].getText() == b[i][j].getText()) {
            d++;
            h--;
        }
        if (d > 4) return true;
        // kiểm tra đường chéo 1
        h = i; k = j; d = 0;
        while (k < 10 && h < 10 && b[i][j].getText() == b[h][k].getText()) {
            d++;
            h++;
            k++;
        }
        h = i - 1; k = j - 1;
        while (k >= 0 && h >= 0 && b[i][j].getText() == b[h][k].getText()) {
            d++;
            h--;
            k--;
        }
        if (d > 4) return true;
        // kiểm tra đường chéo 2
        h = i; k = j; d = 0;
        while (k >= 0 && h < 10 && b[i][j].getText() == b[h][k].getText()) {
            d++;
            h++;
            k--;
        }
        h = i - 1; k = j + 1;
        while (h >= 0 && k < 10 && b[i][j].getText() == b[h][k].getText()) {
            d++;
            h--;
            k++;
        }
        if (d > 4) return true;
        // Nếu không thỏa mãn thì trả về false
        return false;
    }

    //Khởi tạo lại điểm
    public void resetDiem(Label score1, Label score2){
        score1.setText("0");
        score2.setText("0");
    }

    public void resetDiemMay(Label score2){
        score2.setText("0");
    }


    //Thông báo chiến thắng - Người chơi thứ 1
    public void thongBaoChienThang1( Label score1){
        scoreX++;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo chiến thắng");
        alert.setContentText("Người chơi 1 chiến thắng! ");
        alert.setHeaderText(null);
        dialog = alert.getDialogPane();
        dialog.setStyle("-fx-background-color: #FFF8DC;-fx-border-color: #B8860B; -fx-font-size: 14px;");
        dialog.getStyleClass().add("dialog");
        dialog.lookupButton(ButtonType.OK).setStyle("-fx-font-family: Unispace; -fx-background-color:  #DAA520; -fx-font-color: #c2d453;");
        Sound sound = new Sound();
        sound.soundWin();
        Optional<ButtonType> option = alert.showAndWait();
        if ( option.get()==ButtonType.OK){
            score1.setText(String.valueOf(scoreX));
        }
    }


    //Thông báo chiến thắng - Người chơi thứ 2
    public void thongBaoChienThang2( Label score2){
        scoreO++;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo chiến thắng");
        alert.setContentText("Người chơi 2 chiến thắng! ");
        alert.setHeaderText(null);
        dialog = alert.getDialogPane();
        dialog.setStyle("-fx-background-color: #FFF8DC;-fx-border-color: #B8860B; -fx-font-size: 14px;");
        dialog.getStyleClass().add("dialog");
        dialog.lookupButton(ButtonType.OK).setStyle("-fx-font-family: Unispace; -fx-background-color:  #DAA520; -fx-font-color: #c2d453;");
        Sound sound = new Sound();
        sound.soundWin();
        Optional<ButtonType> option = alert.showAndWait();
        if ( option.get()==ButtonType.OK){
            score2.setText(String.valueOf(scoreO));
        }
    }


//---//  Xử lý đánh 1 người (Đánh với máy)

    //Kiểm tra số quân cờ cùng loại trên bàn cờ (Số lượng tùy chọn)
    public boolean demQuanCo(int i, int j, int dem) {
        int d = 0, k = i, h;
        // kiểm tra hàng
        while (k < 10 && b[k][j].getText() == b[i][j].getText()) {
            d++;
            k++;
        }
        k = i - 1;
        while (k >= 0 && b[k][j].getText() == b[i][j].getText()) {
            d++;
            k--;
        }
        if (d > dem) return true;
        d = 0; h = j;
        // kiểm tra cột
        while (h < 10 && b[i][h].getText() == b[i][j].getText()) {
            d++;
            h++;
        }
        h = j - 1;
        while (h >= 0 && b[i][h].getText() == b[i][j].getText()) {
            d++;
            h--;
        }
        if (d > dem) return true;
        // kiểm tra đường chéo 1
        h = i; k = j; d = 0;
        while (k < 10 && h < 10 && b[i][j].getText() == b[h][k].getText()) {
            d++;
            h++;
            k++;
        }
        h = i - 1; k = j - 1;
        while (k >= 0 && h >= 0 && b[i][j].getText() == b[h][k].getText()) {
            d++;
            h--;
            k--;
        }
        if (d > dem) return true;
        // kiểm tra đường chéo 2
        h = i; k = j; d = 0;
        while (k >= 0 && h < 10 && b[i][j].getText() == b[h][k].getText()) {
            d++;
            h++;
            k--;
        }
        h = i - 1; k = j + 1;
        while (h >= 0 && k < 10 && b[i][j].getText() == b[h][k].getText()) {
            d++;
            h--;
            k++;
        }
        if (d > dem) return true;
        // Nếu không thỏa mãn thì trả về false
        return false;
    }


    //Nước cờ chiến thắng - Bot
    public void mayCheck1() {
        int count = 4;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (n[x][y] == 0) {
                    b[x][y].setText("O");
                    if (demQuanCo(x, y, count)) {
                        n[x][y] = 0;
                        b[x][y].setText("");
                        i1 = x;
                        j1 = y;
                        return;
                    }
                    n[x][y] = 0;
                    b[x][y].setText("");
                    }
                i1=0;
                j1=0;
                }
            }
        }


    //Nước cờ chặng người chơi chiến thắng - Bot
    public void mayCheck2() {
        int count = 4;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (n[x][y] == 0) {
                    b[x][y].setText("X");
                    if (demQuanCo(x,y,count)) {
                        n[x][y] = 0;
                        b[x][y].setText("");
                        i1 = x;
                        j1 = y;
                        return;
                    }
                    n[x][y] = 0;
                    b[x][y].setText("");
                    }
                i1=0;
                j1=0;
                }
            }
        }


    //Nước cờ thêm vào quân cờ khi được 3 quân cờ - Bot
    public void mayRandom1() {
        int count = 3;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (n[x][y] == 0) {
                    b[x][y].setText("O");
                    if (demQuanCo(x, y, count)) {
                        n[x][y] = 0;
                        b[x][y].setText("");
                        i1 = x;
                        j1 = y;
                        return;
                    }
                    n[x][y] = 0;
                    b[x][y].setText("");
                }
                i1 = 0;
                j1 = 0;
            }
        }
    }


    //Nước cờ ngẫu nhiên trên bàn cờ - Bot
    public void mayRandom2() {
        if (n[4][4] == 0) {
            i1 = 4;
            j1 = 4;
            return;
        }
        int k = 0;
        for (int x = 0; x < 10; x++){
            for (int y = 0; y < 10; y++) {
                if (n[x][y] == 0)
                    k++;
            }
        }
        int h = (int) ((k - 1) * Math.random() + 1);
        k = 0;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (n[x][y] == 0) {
                    k++;
                    if (k == h) {
                        i1 = x;
                        j1 = y;
                        return;
                    }
                }
            }
        }
        return;
    }


    //Nước cờ tối ưu - Bot
    public void mayDanh(StackPane stackPane, Label score2){
        mayCheck1();
        if (i1 != 0 && j1 !=0){
            if (n[i1][j1] == 0){
                b[i1][j1].setText("O");
                b[i1][j1].setTextFill(Color.BLUE);
                b[i1][j1].setFont(Font.font(18));
                b[i1][j1].setStyle("-fx-background-color:  #FFF8DC; -fx-border-color: #B8860B; -fx-border-width: 0.5px;");
                n[i1][j1] = 1;
                if (ktraThang(i1, j1)) {
                    thongBaoMayThang();
                    i1=0;
                    j1=0;
                    themBanCo(stackPane);
                    danhCoMay(stackPane, score2);

                }
            }
        } else
            mayCheck2();
            if (i1 != 0 && j1 !=0){
                if (n[i1][j1] == 0){
                    b[i1][j1].setText("O");
                    b[i1][j1].setTextFill(Color.BLUE);
                    b[i1][j1].setFont(Font.font(18));
                    b[i1][j1].setStyle("-fx-background-color:  #FFF8DC; -fx-border-color: #B8860B; -fx-border-width: 0.5px;");
                    n[i1][j1] = 1;
                    if (ktraThang(i1, j1)) {
                        thongBaoMayThang();
                        i1=0;
                        j1=0;
                        themBanCo(stackPane);
                        danhCoMay(stackPane, score2);
                    }
                }
            }
            else
                mayRandom1();
                if (i1 != 0 && j1 !=0) {
                    if (n[i1][j1] == 0) {
                        b[i1][j1].setText("O");
                        b[i1][j1].setTextFill(Color.BLUE);
                        b[i1][j1].setFont(Font.font(18));
                        b[i1][j1].setStyle("-fx-background-color:  #FFF8DC; -fx-border-color: #B8860B; -fx-border-width: 0.5px;");
                        n[i1][j1] = 1;
                        if (ktraThang(i1, j1)) {
                            thongBaoMayThang();
                            i1=0;
                            j1=0;
                            themBanCo(stackPane);
                            danhCoMay(stackPane, score2);
                        }
                    }

//
                }else
                    mayRandom2();
                    if (i1 != 0 && j1 !=0){
                        if (n[i1][j1] == 0){
                            b[i1][j1].setText("O");
                            b[i1][j1].setTextFill(Color.BLUE);
                            b[i1][j1].setFont(Font.font(18));
                            b[i1][j1].setStyle("-fx-background-color:  #FFF8DC; -fx-border-color: #B8860B; -fx-border-width: 0.5px;");
                            n[i1][j1] = 1;
                            if (ktraThang(i1, j1)) {
                                thongBaoMayThang();
                                i1=0;
                                j1=0;
                                themBanCo(stackPane);
                                danhCoMay(stackPane, score2);
                            }
                        }
                    }

    }


    ///Xử lý đánh cờ (Người - Máy)
    public void danhCoMay(StackPane stackPane, Label score2){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                n[i][j] = 0; //Mảng xác định ô đã đánh 0: rỗng || 1: Khác rỗng
                int finalI = i;
                int finalJ = j;
                this.b[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    public static int k = 0;
                    @Override
                    public void handle(ActionEvent e) {
                        if (n[finalI][finalJ] == 0) {
                            BanCo.this.b[finalI][finalJ].setText("X");
                            BanCo.this.b[finalI][finalJ].setTextFill(Color.RED);
                            BanCo.this.b[finalI][finalJ].setFont(Font.font(18));
                            BanCo.this.b[finalI][finalJ].setStyle("-fx-background-color:  #FFF8DC; -fx-border-color: #B8860B; -fx-border-width: 0.5px;");
                            Sound sound = new Sound();
                            sound.soundPlayChess();
                            k = 1;
                            n[finalI][finalJ] = 1;
                            if (ktraThang(finalI, finalJ)) {
                                thongBaoNguoiThang(score2);
                                i1=0;
                                j1=0;
                                themBanCo(stackPane);
                                danhCoMay(stackPane, score2);
                                k = 1;
                            }
                            mayDanh(stackPane, score2);
                        }
                    }
                });
            }
        }
    }


    //Thông báo máy thắng (Người - Máy)
    public void thongBaoMayThang(){
        scoreX++;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo kết quả");
        alert.setContentText("Bạn đã thua! ");
        alert.setHeaderText(null);
        dialog = alert.getDialogPane();
        dialog.setStyle("-fx-background-color: #FFF8DC;-fx-border-color: #B8860B; -fx-font-size: 14px;");
        dialog.getStyleClass().add("dialog");
        dialog.lookupButton(ButtonType.OK).setStyle("-fx-font-family: Unispace; -fx-background-color:  #DAA520; -fx-font-color: #c2d453;");
        Sound sound = new Sound();
        sound.soundLose();
        Optional<ButtonType> option = alert.showAndWait();
        if ( option.get()==ButtonType.OK){
        }
    }


    //Thông báo người thắng (Người - Máy)
    public void thongBaoNguoiThang( Label score2){
        scoreO++;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo chiến thắng");
        alert.setContentText("Bạn đã chiến thắng!");
        alert.setHeaderText(null);
        dialog = alert.getDialogPane();
        dialog.setStyle("-fx-background-color: #FFF8DC;-fx-border-color: #B8860B; -fx-font-size: 14px;");
        dialog.getStyleClass().add("dialog");
        dialog.lookupButton(ButtonType.OK).setStyle("-fx-font-family: Unispace; -fx-background-color:  #DAA520; -fx-font-color: #c2d453;");
        Sound sound = new Sound();
        sound.soundWin();
        Optional<ButtonType> option = alert.showAndWait();
        if ( option.get()==ButtonType.OK){
            score2.setText(String.valueOf(scoreO));
        }
    }
}
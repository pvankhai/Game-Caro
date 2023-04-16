package com.example.gamecaro;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Sound {
    //Khai báo
    public static Clip clipBG;

    //Âm thanh nền trò chơi
    public void onBackgroundMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("Background.wav"));
            clipBG = AudioSystem.getClip();
            clipBG.open(audioInputStream);
            clipBG.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    //Tắt âm thanh nền trò chơi
    public void  offBackgroundMusic(){
        clipBG.stop();
        clipBG.close();
    }

    //Âm thanh chọn button
    public void soundClick() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("click2.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    //Âm thanh đánh cờ
    public void soundPlayChess() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("click.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    //Âm thanh chiến thắng
    public void soundWin() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("win.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    //Âm thanh thua cuộc
    public void soundLose() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("lose.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}

package uet.oop.bomberman.gameManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;

import javax.sound.sampled.Clip;

public class MessageDisplay extends Pane {
    // Dùng cho hiển thị mạng người chơi.
    private Timeline heart_animation;
    private Label heart_label;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;

    // Dùng cho hiển thị level hiện tại.
    private Timeline level_animation;
    private int time_level_disappear = 2;
    Label level_label = new Label();
    public static boolean changeLevel = false;

    // Dùng cho hiển thị điểm.
    private Timeline score_animation;
    Label score_label = new Label("Scores: 0");

    // Dùng cho hiển thị thời gian chơi.
    private Timeline time_animation;
    private int time = 300;
    private String str = "";
    Label time_label = new Label(String.valueOf(5));
    public static boolean nextLevel = false;

    // Dùng cho hiển thị thông báo thắng hoặc thua.
    private Timeline winOrLose_animation;
    Label winOrLose_label = new Label();

    // Dùng để hiển thị trạng thái music
    private Timeline music_status_animation;
    private Label music_status_Label = new Label("Music: On");


    public MessageDisplay() {
        // Dùng cho hiển thị mạng người chơi.
        heart_label = new Label("Hearts: ");
        heart_label.setFont(Font.font(20));
        heart_label.setTranslateX(380);

        Image image = new Image("/textures/heart.png");

        img1 = new ImageView(image);
        img1.setTranslateX(450);

        img2 = new ImageView(image);
        img2.setTranslateX(480);

        img3 = new ImageView(image);
        img3.setTranslateX(510);

        getChildren().add(heart_label);
        getChildren().add(img1);
        getChildren().add(img2);
        getChildren().add(img3);

        heart_animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> heartImg()));
        heart_animation.setCycleCount(Timeline.INDEFINITE);
        heart_animation.play();

        // Dùng cho hiển thị level hiện tại.
        level_label.setFont(Font.font(40));
        level_label.setTextFill(Color.web("#cc1414"));
        level_label.setTranslateX(370);
        level_label.setTranslateY(208);
        getChildren().add(level_label);
        level_animation = new Timeline(new KeyFrame(Duration.millis(600), e -> level_label()));
        level_animation.setCycleCount(Timeline.INDEFINITE);
        level_animation.play();

        // Dùng cho hiển thị điểm.
        score_label.setFont(Font.font(20));
        score_label.setTranslateX(800);
        getChildren().add(score_label);
        score_animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> ScoreLabel()));
        score_animation.setCycleCount(Timeline.INDEFINITE);
        score_animation.play();

        // Dùng cho hiển thị thời gian chơi.
        time_label.setFont(Font.font(20));
        time_label.setTranslateX(70);
        getChildren().add(time_label);
        time_animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timelabel()));
        time_animation.setCycleCount(Timeline.INDEFINITE);
        time_animation.play();

        // Dùng cho hiển thị thông báo thắng hoặc thua.
        winOrLose_label.setFont(Font.font(40));
        winOrLose_label.setTextFill(Color.web("#cc1414"));
        winOrLose_label.setTranslateX(370);
        winOrLose_label.setTranslateY(208);
        getChildren().add(winOrLose_label);
        winOrLose_animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> winOrLose_label()));
        winOrLose_animation.setCycleCount(Timeline.INDEFINITE);
        winOrLose_animation.play();

        // Dùng để stop music
        music_status_Label.setFont(Font.font(20));
        music_status_Label.setTranslateX(994);
        music_status_Label.setTranslateY(420);
        music_status_Label.borderProperty();
        music_status_Label.setTextFill(Color.web("#fffdfd"));
        music_status_Label.setStyle("-fx-border-color:black; -fx-background-color: #f94794;");
        getChildren().add(music_status_Label);
        music_status_animation = new Timeline(new KeyFrame(Duration.millis(100), e -> changeMusicStatus()));
        music_status_animation.setCycleCount(Timeline.INDEFINITE);
        music_status_animation.play();
    }

    public void heartImg() {
        switch (Bomber.bomber_life) {
            case 2:
                getChildren().remove(img3);
                break;
            case 1:
                getChildren().remove(img2);
                break;
            case 0:
                getChildren().remove(img1);
                break;
        }
    }

    public void level_label() {
        String str = "";
        if (time_level_disappear > 0) {
            time_level_disappear--;
            str = "Level " + BombermanGame.load_map_level;
            level_label.setText(str);
        } else {
            str = "";
            level_label.setText(str);
        }

        if (changeLevel) {
            time_level_disappear = 2;
            changeLevel = false;
        }
    }
    public void ScoreLabel() {
        String score = ("Scores: " + String.valueOf(BombermanGame.get_points()));
        score_label.setText(score);
    }

    public void timelabel() {
        if (time > 0) {
            time--;
        } else {
            System.exit(0);
        }
        int second = time % 60;
        int minute = time / 60;
        String seconds;
        String minutes;
        if (second < 10) {
            seconds = ("0" + String.valueOf(second));
        } else {
            seconds = (String.valueOf(second));
        }
        minutes = String.valueOf(minute);
        str = ("Time: " + minutes + ":" + seconds);
        if (nextLevel) {
            time += 180;
            str = ("Time: " + minutes + ":" + seconds + " +3p");
            nextLevel = false;
        }
        time_label.setText(str);
    }

    public void winOrLose_label() {
        System.out.println(Bomber.bomber_life);
        if(Bomber.bomber_life <= 0) {
            winOrLose_label.setText("YOU LOSE!");
        }
    }
    boolean isClicked = true;
    public void changeMusicStatus() {
        if(BombermanGame.input.changeMusicStatus) {
            if(isClicked) {
                music_status_Label.setText("Music: Off");
                BombermanGame.THREAD_SOUNDTRACK.stop();
                isClicked = false;
            } else {
                music_status_Label.setText("Music: On");
                BombermanGame.THREAD_SOUNDTRACK.loop(Clip.LOOP_CONTINUOUSLY);
                isClicked = true;
            }
        }
        BombermanGame.input.changeMusicStatus = false;
    }
}

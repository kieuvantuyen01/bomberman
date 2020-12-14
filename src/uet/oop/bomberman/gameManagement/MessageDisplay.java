package uet.oop.bomberman.gameManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteSheet;
import uet.oop.bomberman.gui.CurrentGamePlaySummarizationJframe;

import javax.sound.sampled.Clip;

import static uet.oop.bomberman.BombermanGame.isPauseGame;

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

    // Dùng để pause game
    private Timeline game_status_animation;
    private Label game_status_Label = new Label("   Pause   ");

    // Dùng để hiển thị skin người chơi
    private Timeline skin_animation;
    private ImageView player_skin_1;
    private ImageView player_skin_2;
    private Label player_skin_label1;
    private Label player_skin_label2;
    public static int player_skin_number = 1;

    public MessageDisplay() {
        // Dùng cho hiển thị mạng người chơi.
        heart_label = new Label("Hearts: ");
        heart_label.setFont(Font.font(20));
        heart_label.setTranslateX(380);
        heart_label.setTranslateY(8);

        Image image = new Image("/textures/heart.png");

        img1 = new ImageView(image);
        img1.setTranslateX(450);
        img1.setTranslateY(10);

        img2 = new ImageView(image);
        img2.setTranslateX(480);
        img2.setTranslateY(10);

        img3 = new ImageView(image);
        img3.setTranslateX(510);
        img3.setTranslateY(10);

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
        score_label.setTranslateY(8);
        getChildren().add(score_label);
        score_animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> ScoreLabel()));
        score_animation.setCycleCount(Timeline.INDEFINITE);
        score_animation.play();

        // Dùng cho hiển thị thời gian chơi.
        time_label.setFont(Font.font(20));
        time_label.setTranslateX(70);
        time_label.setTranslateY(8);
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
        music_status_Label.setTextFill(Color.web("#fffdfd"));
        music_status_Label.setStyle("-fx-border-color:black; -fx-background-color: #f94794;");
        getChildren().add(music_status_Label);
        music_status_animation = new Timeline(new KeyFrame(Duration.millis(100), e -> changeMusicStatus()));
        music_status_animation.setCycleCount(Timeline.INDEFINITE);
        music_status_animation.play();

        // Dùng để pause game
        game_status_Label.setFont(Font.font(20));
        game_status_Label.setTranslateX(994);
        game_status_Label.setTranslateY(386);
        game_status_Label.setTextFill(Color.web("#fffdfd"));
        game_status_Label.setStyle("-fx-border-color:black; -fx-background-color: #f94794;");
        getChildren().add(game_status_Label);
        game_status_animation = new Timeline(new KeyFrame(Duration.millis(100), e -> pauseGame()));
        game_status_animation.setCycleCount(Timeline.INDEFINITE);
        game_status_animation.play();

        // Dùng để hiển thị skin người chơi
        Image img_skin_1= new Image("/textures/char1.png");
        Image img_skin_2 = new Image("/textures/char2.png");

        player_skin_1 = new ImageView(img_skin_1);
        player_skin_label1 = new Label("",player_skin_1);
        player_skin_label1.setTranslateX(994);
        player_skin_label1.setTranslateY(40);
        player_skin_label1.setStyle("-fx-border-color: black; -fx-background-color: pink;");
        player_skin_2 = new ImageView(img_skin_2);
        player_skin_label2 = new Label("",player_skin_2);
        player_skin_label2.setTranslateX(994);
        player_skin_label2.setTranslateY(210);

        getChildren().add(player_skin_label1);
        getChildren().add(player_skin_label2);

        skin_animation = new Timeline(new KeyFrame(Duration.millis(150), e -> setSkin()));
        skin_animation.setCycleCount(Timeline.INDEFINITE);
        skin_animation.play();
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
            if (!isPauseGame){
                time--;
            }
        } else {
            new CurrentGamePlaySummarizationJframe().setVisible(true);
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
    boolean isStopMusic = true;
    public void changeMusicStatus() {
        if(BombermanGame.input.changeMusicStatus) {
            if(isStopMusic) {
                music_status_Label.setText("Music: Off");
                BombermanGame.THREAD_SOUNDTRACK.stop();
                isStopMusic = false;
            } else {
                music_status_Label.setText("Music: On");
                BombermanGame.THREAD_SOUNDTRACK.loop(Clip.LOOP_CONTINUOUSLY);
                isStopMusic = true;
            }
            BombermanGame.input.changeMusicStatus = false;
        }
    }


    public void pauseGame() {
        if(BombermanGame.input.pause) {
            if(isPauseGame) {
                game_status_Label.setText("   Pause   ");
                isPauseGame = false;
            } else {
                game_status_Label.setText(" Continue ");
                isPauseGame = true;
            }
        }
    }

    public void setSkin() {
        if(BombermanGame.input.changeSkin) {
            if (player_skin_number == 1) {
                player_skin_label2.setStyle("-fx-border-color: black; -fx-background-color: pink;");
                player_skin_label1.setStyle("-fx-border-color: pink; -fx-background-color: pink;");
                player_skin_number = 2;
                SpriteSheet._path = "/textures/classic2.png";
                //SpriteSheet.tiles = new SpriteSheet("/textures/classic2.png", 512);
            } else {
                player_skin_label2.setStyle("-fx-border-color: pink; -fx-background-color: pink;");
                player_skin_label1.setStyle("-fx-border-color: black; -fx-background-color: pink;");
                player_skin_number = 1;
                SpriteSheet._path = "/textures/classic1.png";
                //SpriteSheet.tiles = new SpriteSheet("/textures/classic1.png", 512);
            }
        }
    }
}

package uet.oop.bomberman.GameHandling;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;

public class HeartDisplay extends Pane {
    private Timeline animation;
    Label heart_label;
    ImageView img1;
    ImageView img2;
    ImageView img3;

    public HeartDisplay() {
        heart_label = new Label("Hearts: ");
        heart_label.setFont(javafx.scene.text.Font.font(20));
        heart_label.setTranslateX(330);

        Image image = new Image("/textures/heart.png");

        img1 = new ImageView(image);
        img1.setTranslateX(400);

        img2 = new ImageView(image);
        img2.setTranslateX(430);

        img3 = new ImageView(image);
        img3.setTranslateX(460);

        getChildren().add(heart_label);
        getChildren().add(img1);
        getChildren().add(img2);
        getChildren().add(img3);

        animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> heartImg()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public void heartImg() {
        switch (BombermanGame.bomber_life) {
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
}

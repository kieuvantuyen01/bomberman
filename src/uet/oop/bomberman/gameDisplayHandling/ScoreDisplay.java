package uet.oop.bomberman.gameDisplayHandling;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import uet.oop.bomberman.BombermanGame;

public class ScoreDisplay extends Pane {
    private Timeline animation;
    Label score_label = new Label("Scores: 0");

    public ScoreDisplay() {
        score_label.setFont(javafx.scene.text.Font.font(20));
        score_label.setTranslateX(800);
        getChildren().add(score_label);
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> ScoreLabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public void ScoreLabel() {
        String score = ("Scores: " + String.valueOf(BombermanGame.get_points()));
        score_label.setText(score);
    }
}

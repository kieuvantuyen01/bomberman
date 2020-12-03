package uet.oop.bomberman.gameDisplayHandling;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;

public class LevelDisplay extends Pane {
    private Timeline animation;
    private int time_level_disappear = 2;
    Label level_label = new Label();
    public static boolean changeLevel = false;

    public LevelDisplay() {
        level_label.setFont(javafx.scene.text.Font.font(40));
        level_label.setTextFill(Color.web("#cc1414"));
        level_label.setTranslateX(370);
        level_label.setTranslateY(208);
        getChildren().add(level_label);
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> level_label()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
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
        System.out.println("         " + time_level_disappear);
    }
}

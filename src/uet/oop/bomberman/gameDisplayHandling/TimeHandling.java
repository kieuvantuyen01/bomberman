package uet.oop.bomberman.gameDisplayHandling;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class TimeHandling extends Pane {
    private Timeline animation;
    private int time = 300;
    private String str = "";
    Label label = new Label(String.valueOf(5));
    public static boolean nextLevel = false;

    public TimeHandling() {
        label.setFont(javafx.scene.text.Font.font(20));
        label.setTranslateX(70);
        getChildren().add(label);
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timelabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
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
        label.setText(str);
    }
}

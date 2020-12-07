package uet.oop.bomberman;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {

    public boolean up, down, left, right, space, previousLevel, nextLevel , changeMusicStatus, pause;

    public Keyboard() {
        up = false;
        down = false;
        right = false;
        left = false;
        space = false;
        previousLevel = false;
        nextLevel = false;
        changeMusicStatus = false;
        pause = false;
    }

    public void keyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ENTER) {
            space = true;
        }
        if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
            up = true;
        }
        if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
            down = true;
        }
        if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
            left = true;
        }
        if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
            right = true;
        }
        if (event.getCode() == KeyCode.U) {
            previousLevel = true;
        }
        if (event.getCode() == KeyCode.I) {
            nextLevel = true;
        }
        if (event.getCode() == KeyCode.O) {
            changeMusicStatus = true;
        }
        if (event.getCode() == KeyCode.P) {
            pause = true;
        }
    }

    public void keyRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ENTER) {
            space = false;
        }
        if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
            up = false;
        }
        if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
            down = false;
        }
        if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
            left = false;
        }
        if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
            right = false;
        }
        if (event.getCode() == KeyCode.U) {
            previousLevel = false;
        }
        if (event.getCode() == KeyCode.I) {
            nextLevel = false;
        }
        if (event.getCode() == KeyCode.O) {
            changeMusicStatus = false;
        }
        if (event.getCode() == KeyCode.P) {
            pause = false;
        }
    }
}
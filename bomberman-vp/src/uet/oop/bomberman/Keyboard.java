package uet.oop.bomberman;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {

    public boolean up, down, left, right, space;

    public void setDown(boolean down) {
        this.down = down;
    }

    public Keyboard() {
        up = false;
        down = false;
        right = false;
        left = false;
        space = false;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setSpace(boolean space) {
        this.space = space;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void keyRelease() {
        up = false;
        down = false;
        left = false;
        right = false;
        space = false;
    }

    public void keyPressed(KeyEvent event) {
        if (event.getCode()== KeyCode.SPACE||event.getCode()== KeyCode.ENTER){
            space=true;
        }
        if (event.getCode()== KeyCode.W||event.getCode()== KeyCode.UP){
            up=true;
        }
        if (event.getCode()== KeyCode.S||event.getCode()== KeyCode.DOWN){
            down=true;
        }
        if (event.getCode()== KeyCode.A||event.getCode()== KeyCode.LEFT){
            left=true;
        }
        if (event.getCode()== KeyCode.D||event.getCode()== KeyCode.RIGHT){
            right=true;
        }
    }

    public void keyRelease(KeyEvent event) {
        if (event.getCode()== KeyCode.SPACE||event.getCode()== KeyCode.ENTER){
            space=false;
        }
        if (event.getCode()== KeyCode.W||event.getCode()== KeyCode.UP){
            up=false;
        }
        if (event.getCode()== KeyCode.S||event.getCode()== KeyCode.DOWN){
            down=false;
        }
        if (event.getCode()== KeyCode.A||event.getCode()== KeyCode.LEFT){
            left=false;
        }
        if (event.getCode()== KeyCode.D||event.getCode()== KeyCode.RIGHT){
            right=false;
        }
    }
}
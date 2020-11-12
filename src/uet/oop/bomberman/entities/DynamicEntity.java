package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;

import java.awt.*;

public abstract class DynamicEntity extends Entity {
    protected boolean _alive = true;
    protected boolean _moving = false;
    protected int _direction = -1;

    public DynamicEntity() {
    }

    public DynamicEntity(Coordinates tile) {
        super(tile);
    }

    public DynamicEntity(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    public abstract void update();

    protected abstract void handleMove();

    protected abstract void move(double xa, double ya);

    public abstract void die();

    protected abstract void afterDie();

    protected abstract boolean canMove(Rectangle rec);

    public boolean isAlive() {
        return _alive;
    }

    public boolean isMoving() {
        return _moving;
    }

    public int getDirection() {
        return _direction;
    }

    /*protected double getXMessage() {
        return (_x * Game.SCALE) + (_sprite.SIZE / 2 * Game.SCALE);
    }

    protected double getYMessage() {
        return (_y* Game.SCALE) - (_sprite.SIZE / 2 * Game.SCALE);
    }*/

}

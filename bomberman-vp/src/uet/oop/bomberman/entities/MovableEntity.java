package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;

import java.awt.*;

public abstract class MovableEntity extends Entity {
    protected int nextX = pixel.getX();
    protected int nextY = pixel.getY();
    protected boolean _alive = true;
    protected boolean _moving = false;
    protected int _animate = 0;
    protected int _direction = -1;
    protected int _velocity = 0;

    public MovableEntity() {
    }

    public MovableEntity(Coordinates tile) {
        super(tile);
    }

    public MovableEntity(Coordinates tile, Image img) {
        super(tile, img);
    }

    public void stop() {
        nextX = pixel.getX();
        nextY = pixel.getY();
    }

    public void turnRight() {
        nextX = pixel.getX() + _velocity;
    }

    public void turnLeft() {
        nextX = pixel.getX() - _velocity;
    }

    public void goUp() {
        nextX = pixel.getY() - _velocity;
    }

    public void goDown() {
        nextX = pixel.getY() + _velocity;
    }

    @Override
    public abstract void update();

    protected abstract void handleMove();

    protected abstract void move(double xa, double ya);

    public abstract void die();

    protected abstract void afterDie();

    protected abstract boolean canMoveToDirection(int x,int y);

    public void setVelocity(int _velocity) {
        this._velocity = _velocity;
    }

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

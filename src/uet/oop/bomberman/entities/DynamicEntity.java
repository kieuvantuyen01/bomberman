package uet.oop.bomberman.entities;

import java.awt.*;

public abstract class DynamicEntity extends Entity {
    protected boolean _alive = true;
    protected boolean _moving = false;
    protected int _direction = -1;

    public DynamicEntity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public DynamicEntity() {

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

package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;

import java.awt.*;

public abstract class MovableEntity extends Entity implements Animated {
    protected boolean _alive = true;
    protected boolean _moving = false;
    protected int _direction = -1;
    protected int _animate = 0;
    protected Coordinates d = new Coordinates(0, 0);

    public MovableEntity() {
    }

    public MovableEntity(Coordinates tile) {
        super(tile);
    }

    public MovableEntity(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    public abstract void update();

    protected abstract void handleMove();

    protected void move(double xa, double ya){
        if (d.getX() > 0) _direction = 1;
        if (d.getX() < 0) _direction = 3;
        if (d.getY() > 0) _direction = 2;
        if (d.getY() < 0) _direction = 0;

        pixel.setY((int) (pixel.getY() + ya));
        this.rectangle = new Rectangle(pixel.getX(), pixel.getY(), (int) img.getWidth(), (int) img.getHeight());

        pixel.setX((int) (pixel.getX() + xa));
        this.rectangle = new Rectangle(pixel.getX(), pixel.getY(), (int) img.getWidth(), (int) img.getHeight());

        tile=pixel.convertPixelToTile();
    }

    public abstract void die();

    protected abstract void afterDie();

    protected boolean canMoveToDirection(int x,int y){
        Entity entity = BombermanGame.getEntityAt(tile.getX()+x,tile.getY()+y);
        if (entity instanceof Wall || entity instanceof Brick){
            return false;
        }
        return true;
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

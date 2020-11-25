package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;


public abstract class MovableEntity extends Entity implements Animated {
    protected boolean _alive = true;
    protected boolean _moving = false;

    ;
    protected int _animate = 0;
    protected Coordinates d = new Coordinates(0, 0);

    public MovableEntity(Coordinates tile) {
        super(tile);
    }

    @Override
    public abstract void update();

    protected abstract void handleMove();

    protected void move(double xa, double ya) {
        if (d.getX() > 0) _direction = DIRECTION.RIGHT;
        if (d.getX() < 0) _direction = DIRECTION.LEFT;
        if (d.getY() > 0) _direction = DIRECTION.DOWN;
        if (d.getY() < 0) _direction = DIRECTION.UP;

        pixel.setY((int) (pixel.getY() + ya));

        pixel.setX((int) (pixel.getX() + xa));

        tile = pixel.convertPixelToTile();
    }

    public void die() {
        if (!_alive) return;
        this._alive = false;
        _animate = 0;
    }

    protected abstract void afterDie();

    protected boolean canMoveToDirection(int x, int y) {
        Entity entity = BombermanGame.getEntityAt(tile.getX() + x, tile.getY() + y);
        if (entity instanceof Wall || entity instanceof Brick || entity instanceof Bomb) {
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

    public DIRECTION getDirection() {
        return _direction;
    }


    @Override
    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        img = sprite1.getFxImage();
        if (_moving) {
            img = Sprite.movingSprite(sprite2.getFxImage(), sprite3.getFxImage(), _animate, 20);
        }
    }

    protected void chooseSprite(Sprite default1,
                                Sprite left1, Sprite left2, Sprite left3,
                                Sprite right1, Sprite right2, Sprite right3,
                                Sprite up1, Sprite up2, Sprite up3,
                                Sprite down1, Sprite down2, Sprite down3) {
        switch (_direction) {
            case UP:
                loadAnimated(up1, up2, up3);
                break;
            case RIGHT:
                loadAnimated(right1, right2, right3);
                break;
            case DOWN:
                loadAnimated(down1, down2, down3);
                break;
            case LEFT:
                loadAnimated(left1, left2, left3);
                break;
            default:
                img = default1.getFxImage();
                break;
        }
    }

    protected abstract void handleCollision();

}
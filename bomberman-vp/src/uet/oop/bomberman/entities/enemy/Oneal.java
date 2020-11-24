package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    protected double speed = 1;
    double xa = -speed, ya = 0;

    public Oneal() {
    }

    public Oneal(Coordinates tile) {
        super(tile);
        img = Sprite.oneal_left1.getFxImage();
    }

    public Oneal(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    protected void handleMove() {
        if (d.getY() == 0 && d.getX() == 0) {
            findBomber();
        }
        if (d.getX() == 0 && ya == 0) {
            if (xa < 0) {
                if (canMoveToDirection(-1, 0)) {
                    d.setX(-Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(1, 0)) {
                    xa = speed;
                    d.setX(Sprite.SCALED_SIZE);
                }
            } else if (xa > 0) {
                if (canMoveToDirection(1, 0)) {
                    d.setX(Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(-1, 0)) {
                    xa = -speed;
                    d.setX(-Sprite.SCALED_SIZE);
                }
            }
        }
        if (d.getY() == 0 && xa == 0) {
            if (ya < 0) {
                if (canMoveToDirection(0, -1)) {
                    d.setY(-Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(0, 1)) {
                    ya = speed;
                    d.setY(Sprite.SCALED_SIZE);
                }
            } else if (ya > 0) {
                if (canMoveToDirection(0, 1)) {
                    d.setY(Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(0, -1)) {
                    ya = -speed;
                    d.setY(-Sprite.SCALED_SIZE);
                }
            }
        }

        if (d.getX() != 0 || d.getY() != 0) {
            move(xa * Sprite.PLAYERSPEED, ya * Sprite.PLAYERSPEED);
            d.setX((int) (d.getX() - xa * Sprite.PLAYERSPEED));
            d.setY((int) (d.getY() - ya * Sprite.PLAYERSPEED));
            _moving = true;
        } else {
            _moving = false;
        }
    }

    @Override
    public void animate() {
        if (_animate > 6000) _animate = 0;
        else _animate++;
    }


    @Override
    public void update() {
        if (_alive == false) {
            afterDie();
            return;
        }
        animate();

        handleMove();

        chooseSprite(Sprite.oneal_left1,
                Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,
                Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3,
                Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,
                Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3);


    }

    @Override
    protected void handleCollision() {

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }

    private void findBomber() {
        if (BombermanGame.getBomber() == null) {
            return;
        }
        double xtemp = 0, ytemp = 0;
        int low, high;
        speed = 1;
        if (tile.getX() == BombermanGame.getBomber().getTile().getX()) {
            xtemp = 0;
            if (tile.getY() < BombermanGame.getBomber().getTile().getY()) {
                ytemp = speed;
                low = tile.getY();
                high = BombermanGame.getBomber().getTile().getY();
            } else if (tile.getY() > BombermanGame.getBomber().getTile().getY()) {
                ytemp = -speed;
                low = BombermanGame.getBomber().getTile().getY();
                high = tile.getY();
            } else {
                xa = xtemp;
                ya = ytemp;
                return;
            }
            for (int i = low + 1; i < high; i++) {
                if (BombermanGame.getEntityAt(tile.getX(), i) instanceof Wall
                        || BombermanGame.getEntityAt(tile.getX(), i) instanceof Brick
                        || BombermanGame.getEntityAt(tile.getX(), i) instanceof Bomb) {
                    return;
                }
            }
            xa = xtemp;
            ya = ytemp;
            return;
        } else if (tile.getY() == BombermanGame.getBomber().getTile().getY()) {
            ytemp = 0;
            if (tile.getX() < BombermanGame.getBomber().getTile().getX()) {
                xtemp = speed;
                low = tile.getX();
                high = BombermanGame.getBomber().getTile().getX();
            } else if (tile.getX() > BombermanGame.getBomber().getTile().getX()) {
                xtemp = -speed;
                low = BombermanGame.getBomber().getTile().getX();
                high = tile.getX();
            } else {
                ya = ytemp;
                xa = xtemp;
                return;
            }

            for (int i = low + 1; i < high; i++) {
                if (BombermanGame.getEntityAt(i, tile.getY()) instanceof Wall
                        || BombermanGame.getEntityAt(i, tile.getY()) instanceof Brick
                        || BombermanGame.getEntityAt(i, tile.getY()) instanceof Bomb) {
                    return;
                }
            }
            xa = xtemp;
            ya = ytemp;
        }
        speed = 1;
        return;
    }
}


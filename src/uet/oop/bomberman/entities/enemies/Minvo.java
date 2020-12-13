package uet.oop.bomberman.entities.enemies;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Minvo extends Enemy {
    private static Random random = new Random();
    int direc = 0;

    public Minvo(Coordinates tile) {
        super(tile, 100);
        img = Sprite.minvo_left1.getFxImage();
        ya = -speed;
    }

    @Override
    protected void handleDirection() {
        if (d.getX() == 0) {
            if (Math.abs(xa) == speed) {
                do {
                    direc = random.nextInt(3);
                    direc--;
                } while (direc == 0);
                if (canMoveToDirection(0, direc)
                        || canMoveToDirection(0, -direc)) {
                    if (!canMoveToDirection(0, direc)) {
                        direc = -direc;
                    }
                    xa = 0;
                    ya = direc * speed;
                    d.setY(direc * Sprite.SCALED_SIZE);
                } else {
                    int xt = xa / Math.abs(xa);
                    if (canMoveToDirection(xt, 0)
                            || canMoveToDirection(-xt, 0)) {
                        if (canMoveToDirection(xt, 0)) {
                            d.setX(xa * Sprite.SCALED_SIZE);
                        } else {
                            xa = -xa;
                            d.setX(xa * Sprite.SCALED_SIZE);
                        }
                    }
                }
            }
        }
        if (d.getY() == 0) {
            if (Math.abs(ya) == speed) {
                do {
                    direc = random.nextInt(3);
                    direc--;
                } while (direc == 0);
                if (canMoveToDirection(direc, 0)
                        || canMoveToDirection(-direc, 0)) {
                    if (!canMoveToDirection(direc, 0)) {
                        direc = -direc;
                    }
                    ya = 0;
                    xa = direc * speed;
                    d.setX(direc * Sprite.SCALED_SIZE);
                } else {
                    int yt = ya / Math.abs(ya);
                    if (canMoveToDirection(0, yt)
                            || canMoveToDirection(0, -yt)) {
                        if (canMoveToDirection(0, yt)) {
                            d.setY(ya * Sprite.SCALED_SIZE);
                        } else {
                            ya = -ya;
                            d.setY(ya * Sprite.SCALED_SIZE);
                        }
                    }
                }
            }
        }

        handleMove();
    }

    @Override
    public void update() {
        super.update();
        if (!_alive) {
            return;
        }

        handleDirection();

        chooseSprite(Sprite.minvo_right1,
                Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3,
                Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3,
                Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3,
                Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3);

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }

    @Override
    protected void handleCollision() {

    }

    @Override
    protected void afterDie() {
        img = Sprite.minvo_dead.getFxImage();
    }
}

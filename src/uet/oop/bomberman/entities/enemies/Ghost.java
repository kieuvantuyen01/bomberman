package uet.oop.bomberman.entities.enemies;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Ghost extends Enemy {

    public Ghost(Coordinates tile, boolean horizontal) {
        super(tile, 200);
        img = Sprite.ghost_left1.getFxImage();
        if (horizontal) {
            xa = -speed;
        } else {
            ya = -speed;
        }
    }

    @Override
    protected void handleDirection() {
        if (d.getX() == 0) {
            if (xa == -speed) {
                if (canMoveToDirection(-1, 0)) {
                    d.setX(-Sprite.SCALED_SIZE);
                } else {
                    xa = 0;
                    if (canMoveToDirection(0, -1)) {
                        ya = -speed;
                    } else {
                        ya = speed;
                    }
                }
            } else if (xa == speed) {
                if (canMoveToDirection(1, 0)) {
                    d.setX(Sprite.SCALED_SIZE);
                } else {
                    xa = 0;
                    if (canMoveToDirection(0, 1)) {
                        ya = speed;
                    } else {
                        ya = -speed;
                    }
                }
            }
        }
        if (d.getY() == 0) {
            if (ya == -speed) {
                if (canMoveToDirection(0, -1)) {
                    d.setY(-Sprite.SCALED_SIZE);
                } else {
                    ya = 0;
                    if (canMoveToDirection(1, 0)) {
                        xa = speed;
                    } else {
                        xa = -speed;
                    }
                }
            } else if (ya == speed) {
                if (canMoveToDirection(0, 1)) {
                    d.setY(Sprite.SCALED_SIZE);
                } else {
                    ya = 0;
                    if (canMoveToDirection(-1, 0)) {
                        xa = -speed;
                    } else {
                        xa = speed;
                    }
                }
            }
        }

        handleMove();
    }

    @Override
    public void animate() {
        if (_animate > 6000) _animate = 0;
        else _animate++;
    }

    @Override
    public void update() {
        super.update();
        if (!_alive) {
            return;
        }
        handleDirection();

        chooseSprite(Sprite.ghost_left1,
                Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3,
                Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3,
                Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left1,
                Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right1);

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        Entity entity = BombermanGame.getEntityAt(tile.getX() + x, tile.getY() + y);
        return !(entity instanceof Wall);
    }

    @Override
    protected void handleCollision() {

    }

    @Override
    protected void afterDie() {
        img = Sprite.ghost_dead.getFxImage();
    }
}

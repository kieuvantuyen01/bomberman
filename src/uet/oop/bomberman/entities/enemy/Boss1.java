package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class Boss1 extends Boss {
    protected double speed = 1;
    double xa = -speed, ya = 0;

    public Boss1(Coordinates tile) {
        super(tile, 1000);
        img = new Image("textures/boss_down1.png");
        tiles.add(tile);
        tiles.add(new Coordinates(tile.getX() + 1, tile.getY()));
        tiles.add(new Coordinates(tile.getX(), tile.getY() + 1));
        tiles.add(new Coordinates(tile.getX() + 1, tile.getY() + 1));
    }

    @Override
    protected void handleMove() {
        if (d.getX() == 0) {
            if (xa == -speed) {
                if (canMoveToDirection(-1, 0) && canMoveToDirection(-1, 1)) {
                    d.setX(-Sprite.SCALED_SIZE);
                } else {
                    xa = 0;
                    if (canMoveToDirection(0, -1) && canMoveToDirection(1, -1)) {
                        ya = -speed;
                    } else {
                        ya = speed;
                    }
                }
            } else if (xa == speed) {
                if (canMoveToDirection(2, 0) && canMoveToDirection(2, 1)) {
                    d.setX(Sprite.SCALED_SIZE);
                } else {
                    xa = 0;
                    if (canMoveToDirection(0, 2) && canMoveToDirection(1, 2)) {
                        ya = speed;
                    } else {
                        ya = -speed;
                    }
                }
            }
        }
        if (d.getY() == 0) {
            if (ya == -speed) {
                if (canMoveToDirection(0, -1) && canMoveToDirection(1, -1)) {
                    d.setY(-Sprite.SCALED_SIZE);
                } else {
                    ya = 0;
                    if (canMoveToDirection(2, 0) && canMoveToDirection(2, 1)) {
                        xa = speed;
                    } else {
                        xa = -speed;
                    }
                }
            } else if (ya == speed) {
                if (canMoveToDirection(0, 2) && canMoveToDirection(1, 2)) {
                    d.setY(Sprite.SCALED_SIZE);
                } else {
                    ya = 0;
                    if (canMoveToDirection(-1, 0) && canMoveToDirection(-1, 1)) {
                        xa = -speed;
                    } else {
                        xa = speed;
                    }
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
    public void update() {
        super.update();
        if (!_alive) {
            return;
        }

        handleMove();

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
        tiles.removeAll(tiles);
        tiles.add(tile);
        tiles.add(new Coordinates(tile.getX() + 1, tile.getY()));
        tiles.add(new Coordinates(tile.getX(), tile.getY() + 1));
        tiles.add(new Coordinates(tile.getX() + 1, tile.getY() + 1));
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
    }
}

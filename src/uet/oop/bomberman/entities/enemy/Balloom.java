package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;


public class Balloom extends Enemy {
    protected double speed = 1;
    double xa = 0, ya = 0;

    public Balloom() {
    }

    public Balloom(Coordinates tile, boolean horizontal) {
        super(tile);
        img = Sprite.balloom_left1.getFxImage();
        if (horizontal) {
            xa = -speed;
        } else {
            ya = -speed;
        }
    }

    public Balloom(Coordinates tile, Image img) {
        super(tile, img);
        this.img = img;
    }


    @Override
    protected void handleMove() {
        if (d.getX() == 0) {
            if (xa == -speed) {
                if (canMoveToDirection(-1, 0)) {
                    d.setX(-Sprite.SCALED_SIZE);
                } else {
                    xa = 0;
                    if (canMoveToDirection(0,-1)){
                        ya = -speed;
                    } else {
                        ya=speed;
                    }
                }
            } else if (xa == speed) {
                if (canMoveToDirection(1, 0)) {
                    d.setX(Sprite.SCALED_SIZE);
                } else {
                    xa = 0;
                    if (canMoveToDirection(0,1)){
                        ya = speed;
                    } else {
                        ya=-speed;
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
                    if (canMoveToDirection(1,0)){
                        xa = speed;
                    } else {
                        xa=-speed;
                    }
                }
            } else if (ya == speed) {
                if (canMoveToDirection(0, 1)) {
                    d.setY(Sprite.SCALED_SIZE);
                } else {
                    ya = 0;
                    if (canMoveToDirection(-1,0)){
                        xa = -speed;
                    } else {
                        xa=speed;
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
    public void animate() {
        if (_animate > 6000) _animate = 0;
        else _animate++;
    }

    @Override
    public void chooseSprite() {
        switch (_direction) {
            case 0:
                img = Sprite.balloom_left1.getFxImage();
                if (_moving) {
                    img = Sprite.movingSprite(Sprite.balloom_left2.getFxImage(), Sprite.balloom_left3.getFxImage(), _animate, 20);
                }
                break;
            case 1:
                img = Sprite.balloom_right1.getFxImage();
                if (_moving) {
                    img = Sprite.movingSprite(Sprite.balloom_right2.getFxImage(), Sprite.balloom_right3.getFxImage(), _animate, 20);
                }
                break;
            case 2:
                img = Sprite.balloom_right1.getFxImage();
                if (_moving) {
                    img = Sprite.movingSprite(Sprite.balloom_right2.getFxImage(), Sprite.balloom_right3.getFxImage(), _animate, 20);
                }
                break;
            case 3:
                img = Sprite.balloom_left1.getFxImage();
                if (_moving) {
                    img = Sprite.movingSprite(Sprite.balloom_left2.getFxImage(), Sprite.balloom_left3.getFxImage(), _animate, 20);
                }
                break;
            default:
                img = Sprite.balloom_right1.getFxImage();
                if (_moving) {
                    img = Sprite.movingSprite(Sprite.balloom_right2.getFxImage(), Sprite.balloom_right3.getFxImage(), _animate, 20);
                }
                break;
        }
    }

    @Override
    public void update() {
        if (_alive == false) {
            afterDie();
            return;
        }
        animate();

        handleMove();

        chooseSprite();

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }
}

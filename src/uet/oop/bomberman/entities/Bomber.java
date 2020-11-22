package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.Keyboard;

import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Bomber extends MovableEntity {

    protected BombermanGame game = new BombermanGame();
    protected Keyboard _input = new Keyboard();


    protected static double speed = 1.0;
    protected static int max_bomb = 1;
    protected static boolean flame = false;

    public Bomber() {
        this.img = Sprite.player_right.getFxImage();
    }

    public Bomber(Coordinates tile) {
        super(tile);
        this.img = Sprite.player_right.getFxImage();
        this.pixel.setX(this.pixel.getX()+6);
    }

    public Bomber(Coordinates tile, Image img) {
        super(tile, img);
        this.pixel.setX(this.pixel.getX()+6);
    }

    public Bomber(Coordinates tile, Keyboard _input) {
        super(tile);
        this.img = Sprite.player_right.getFxImage();
        this.pixel.setX(this.pixel.getX()+4);
        this._input = _input;
//        this.rectangle = new Rectangle(this.pixel.getX(), this.pixel.getY(), (int) (img.getWidth()), (int) img.getHeight());
    }

    public Bomber(Coordinates tile, Image img, Keyboard _input) {
        super(tile, img);
        this._input = _input;
        //      this.rectangle = new Rectangle(this.pixel.getX(), this.pixel.getY(), (int) (img.getWidth()), (int) img.getHeight());
    }


    @Override
    public void update() {

        if (_alive == false) {
            afterDie();
            return;
        }
        animate();
        putBomb();
        handleMove();

        chooseSprite(Sprite.player_right,
                Sprite.player_left,Sprite.player_left_1,Sprite.player_left_2,
                Sprite.player_right,Sprite.player_right_1,Sprite.player_right_2,
                Sprite.player_up,Sprite.player_up_1,Sprite.player_up_2,
                Sprite.player_down,Sprite.player_down_1,Sprite.player_down_2);

    }
    protected void putBomb(){
        if (_input.space) {
            if(BombermanGame.getBombs().size() < 1) {
                BombermanGame.setBomb(new Bomb(new Coordinates(tile.getX(), tile.getY())));
            }
        }
    }

    @Override
    public void animate() {
        if (_animate > 6000) _animate = 0;
        else _animate++;
    }



    @Override
    protected void handleMove() {
        double xa = 0, ya = 0;
        if ((_input.up && d.getX()==0 && canMoveToDirection(0,-1)) || d.getY() < 0) {
            ya -= speed;
            if (d.getY() >= 0) d.setY(d.getY() - Sprite.SCALED_SIZE);
        }
        if ((_input.down && d.getX()==0 && canMoveToDirection(0,1))|| d.getY() > 0) {
            ya += speed;
            if (d.getY() <= 0) d.setY(d.getY() + Sprite.SCALED_SIZE);
        }
        if ((_input.left && d.getY()==0 && canMoveToDirection(-1,0)) || d.getX() < 0) {
            xa -= speed;
            if (d.getX() >= 0) d.setX(d.getX() - Sprite.SCALED_SIZE);
        }
        if ((_input.right && d.getY()==0 && canMoveToDirection(1,0)) || d.getX() > 0) {
            xa += speed;
            if (d.getX() <= 0) d.setX(d.getX() + Sprite.SCALED_SIZE);
        }

        if (d.getX() != 0 || d.getY() != 0) {
            move(xa * Sprite.PLAYERSPEED, ya * Sprite.PLAYERSPEED);
            d.setX((int) (d.getX() - xa * Sprite.PLAYERSPEED));
            d.setY((int) (d.getY() - ya * Sprite.PLAYERSPEED));
            _moving = true;
        } else {
            if (_input.up) _direction=DIRECTION.UP;
            if (_input.right) _direction=DIRECTION.RIGHT;
            if (_input.down) _direction=DIRECTION.DOWN;
            if (_input.left) _direction=DIRECTION.LEFT;
            _moving = false;
        }

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    public void die() {
        if (!_alive) return;
        this._alive = false;

        this.img = Sprite.player_dead1.getFxImage();

    }

    @Override
    protected void afterDie() {

    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }



}

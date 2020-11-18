package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;


public class Balloom extends DynamicEntity {

    public Balloom() {
        this.img=Sprite.balloom_left1.getFxImage();
    }

    public Balloom(Coordinates tile) {
        super(tile);
        this.img=Sprite.balloom_left1.getFxImage();
    }

    public Balloom(Coordinates tile, Image img) {
        super(tile, img);
    }

    protected static int timeLife = 60;

    @Override
    public void update() {
        checkDead();
        if (_alive == false) {
            die();
            if (timeLife < 1) {
                afterDie();
            }
        }
    }

    @Override
    protected void handleMove() {

    }


    public void checkDead() {
        /*Entity c = BombermanGame.getEntity(rectangle);

        if (c instanceof BomBang) {
            kill();
        }*/
    }

    @Override
    protected void move(double xa, double ya) {

    }

    @Override
    public void die() {
        this._alive = false;
        img = Sprite.balloom_dead.getFxImage();
        if (timeLife < 0) {
            timeLife = 40;
        } else {
            timeLife--;
        }
    }

    @Override
    protected void afterDie() {
        /*BombermanGame.entities.remove(this);*/
    }

    @Override
    protected boolean canMoveToDirection(int x,int y) {
        return false;
    }
}

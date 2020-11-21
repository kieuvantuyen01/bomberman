package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;


public class Balloom extends Enemy {

    /*public Balloom() {
        this.img = Sprite.balloom_left1.getFxImage();
    }*/

    /*public Balloom(Coordinates tile) {
        super(tile);
        this.img = Sprite.balloom_left1.getFxImage();
    }*/

    public Balloom(Coordinates tile, Image img) {
        super(tile, img);
        this.img = Sprite.balloom_left1.getFxImage();
        setVelocity(1);
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

/*    @Override
    protected void handleMove() {

    }*/


    public void checkDead() {
        /*Entity c = BombermanGame.getEntity(rectangle);

        if (c instanceof BomBang) {
            kill();
        }*/
    }

/*    @Override
    protected void move(double xa, double ya) {

    }*/

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
        BombermanGame.entities.remove(this);
    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return false;
    }

    private void chooseSprite() {
        switch (_direction) {
            case 0:
                goUp();
                break;
            case 1:
                turnRight();
                break;
            case 2:
                goDown();
                break;
            case 3:
                turnLeft();
                break;
            default:
                turnRight();
                break;
        }
    }

    public void turnLeft() {
        super.turnLeft();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 18).getFxImage();
    }

    public void turnRight() {
        super.turnRight();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 18).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 18).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 18).getFxImage();
    }

    @Override
    protected void handleMove() {


    }

    @Override
    protected void move(double xa, double ya) {
        if (tile.getX() > 0) _direction = 1;
        if (tile.getX() < 0) _direction = 3;
        if (tile.getY() > 0) _direction = 2;
        if (tile.getY() < 0) _direction = 0;

        // if (canMove(this.rectangle)) { //separate the moves for the player can slide when is colliding
        // if (canMove(new Rectangle(pixel.getX(), (int) (pixel.getY() + ya), (int) img.getWidth(), (int) img.getHeight()))) {
        pixel.setY((int) (pixel.getY() + ya));
        this.rectangle = new Rectangle(pixel.getX(), pixel.getY(), (int) img.getWidth(), (int) img.getHeight());
        //}

        //  }

        //   if (canMove(this.rectangle)) {
        // if (canMove(new Rectangle((int) (pixel.getX() + xa), pixel.getY(), (int) img.getWidth(), (int) img.getHeight()))) {
        pixel.setX((int) (pixel.getX() + xa));
        this.rectangle = new Rectangle(pixel.getX(), pixel.getY(), (int) img.getWidth(), (int) img.getHeight());
        // }

        //   }
        tile = pixel.convertPixelToTile();
        System.out.println(tile.getX() + " " + tile.getY());
    }
/*
    @Override
    public void die() {
        if (!_alive) return;
        this._alive = false;

        this.img = Sprite.balloom_dead1.getFxImage();

    }

    @Override
    protected void afterDie() {

    }*/

    /*@Override
    protected boolean canMoveToDirection(int x,int y) {
        Entity entity = BombermanGame.getEntityAt(tile.getX()+x,tile.getY()+y);
        if (entity instanceof Wall || entity instanceof Brick){
            return false;
        }
        return true;
    }*/

}

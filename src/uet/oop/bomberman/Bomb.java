package uet.oop.bomberman;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.MovableEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Bomb extends MovableEntity {

    private int timeCounter = 0;

    public Bomb(Coordinates tile, Image img, int timeCounter) {
        super(tile, img);
        this.timeCounter = timeCounter;
    }

    public Bomb(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    public void update() {
        if (timeCounter++ == 120) {
            explode();
        }
        img = Sprite.movingSprite(Sprite.bomb.getFxImage(), Sprite.bomb_1.getFxImage(),
                Sprite.bomb_2.getFxImage(), timeCounter, 20);
    }

    public void explode() {
        _alive = false;
    }
    public boolean isAllowedToPassThrough(MovableEntity e) {
        Rectangle r1 = getRectangle();
        Rectangle r2;
        if (e instanceof Bomber) {
            Bomber bomber = (Bomber) e;
            r2 = new Rectangle(bomber.getPixel().getX() + 4, bomber.getPixel().getY() + 4, Sprite.SCALED_SIZE * 3 / 4, Sprite.SCALED_SIZE * 3 / 4);
        } else {
            r2 = new Rectangle(e.getPixel().getX(), e.getPixel().getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        }
        return r1.intersects(r2);
    }


    @Override
    protected void handleMove() {

    }

    @Override
    public void die() {

    }

    @Override
    protected void afterDie() {

    }

    @Override
    public void animate() {

    }
}

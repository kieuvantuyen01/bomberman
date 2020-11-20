package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {

    public Oneal() {
    }

    public Oneal(Coordinates tile) {
        super(tile);
        img= Sprite.oneal_left1.getFxImage();
    }

    public Oneal(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    protected void handleMove() {

    }

    @Override
    public void animate() {

    }

    @Override
    public void chooseSprite() {

    }
}

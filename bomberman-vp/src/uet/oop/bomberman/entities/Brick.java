package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {

    public Brick() {
        this.img=Sprite.brick.getFxImage();
    }

    public Brick(Coordinates tile) {
        super(tile);
        this.img=Sprite.brick.getFxImage();
    }

    public Brick(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    public void update() {

    }
}

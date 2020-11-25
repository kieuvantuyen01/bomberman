package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity {

    public Portal() {
        this.img = Sprite.portal.getFxImage();
    }

    public Portal(Coordinates tile) {
        super(tile);
        this.img = Sprite.portal.getFxImage();
    }

    public Portal(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    public void update() {

    }
}

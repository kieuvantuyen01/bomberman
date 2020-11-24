package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class FlamesItem extends Item {

    public FlamesItem() {
        this.img=Sprite.powerup_flames.getFxImage();
    }

    public FlamesItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_flames.getFxImage();
    }

    public FlamesItem(Coordinates tile, Image img) {
        super(tile, img);
    }

}

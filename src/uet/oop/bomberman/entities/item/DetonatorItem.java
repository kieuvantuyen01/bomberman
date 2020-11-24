package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class DetonatorItem extends Item {
    public DetonatorItem() {
        this.img=Sprite.powerup_detonator.getFxImage();
    }

    public DetonatorItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_detonator.getFxImage();
    }

    public DetonatorItem(Coordinates tile, Image img) {
        super(tile, img);
    }


}

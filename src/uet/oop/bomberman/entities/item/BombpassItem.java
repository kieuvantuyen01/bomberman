package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class BombpassItem extends Item {

    public BombpassItem() {
        this.img=Sprite.powerup_bombpass.getFxImage();
    }

    public BombpassItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_bombpass.getFxImage();
    }

    public BombpassItem(Coordinates tile, Image img) {
        super(tile, img);
    }

}

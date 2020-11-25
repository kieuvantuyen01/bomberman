package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class FlamepassItem extends Item {

    public FlamepassItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_flamepass.getFxImage();
    }

}

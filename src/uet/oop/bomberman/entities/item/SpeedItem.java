package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {

    public SpeedItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_speed.getFxImage();
    }

}

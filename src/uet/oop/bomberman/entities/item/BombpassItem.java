package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class BombpassItem extends Item {

    public BombpassItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_bombpass.getFxImage();
    }

}

package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class BombsItem extends Item {

    public BombsItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_bombs.getFxImage();
    }

}

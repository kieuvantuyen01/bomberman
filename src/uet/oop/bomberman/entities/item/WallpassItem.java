package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class WallpassItem extends Item {

    public WallpassItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_wallpass.getFxImage();
    }

    @Override
    public void getItem() {

    }

}

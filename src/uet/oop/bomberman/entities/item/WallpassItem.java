package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class WallpassItem extends Item {

    public WallpassItem() {
        this.img=Sprite.powerup_wallpass.getFxImage();
    }

    public WallpassItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_wallpass.getFxImage();
    }

    public WallpassItem(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    public void update() {

    }
}

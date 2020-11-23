package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
    public SpeedItem() {
        this.img=Sprite.powerup_speed.getFxImage();
    }

    public SpeedItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_speed.getFxImage();
    }

    public SpeedItem(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    public void update() {

    }
}

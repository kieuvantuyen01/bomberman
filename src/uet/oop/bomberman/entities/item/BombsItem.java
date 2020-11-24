package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class BombsItem extends Item {
    public BombsItem() {
        this.img=Sprite.powerup_bombs.getFxImage();
    }

    public BombsItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_bombs.getFxImage();
    }

    public BombsItem(Coordinates tile, Image img) {
        super(tile, img);
    }
    
}

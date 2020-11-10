package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {

    public SpeedItem(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_speed.getFxImage();
    }

    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}

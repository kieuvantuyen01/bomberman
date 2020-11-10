package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class FlamesItem extends Item {

    public FlamesItem(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_flames.getFxImage();
    }

    public FlamesItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}

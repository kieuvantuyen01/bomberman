package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class FlamepassItem extends Item {

    public FlamepassItem(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_flamepass.getFxImage();
    }

    public FlamepassItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}

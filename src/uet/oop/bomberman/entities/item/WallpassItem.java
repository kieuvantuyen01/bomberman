package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class WallpassItem extends Item {

    public WallpassItem(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_wallpass.getFxImage();
    }

    public WallpassItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}

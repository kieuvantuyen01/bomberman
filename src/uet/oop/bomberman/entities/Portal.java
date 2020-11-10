package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity {

    public Portal(int x, int y) {
        super(x, y);
        this.img = Sprite.wall.getFxImage();
    }

    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}

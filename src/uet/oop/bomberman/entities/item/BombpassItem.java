package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class BombpassItem extends Item {

    public BombpassItem(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_bombpass.getFxImage();
    }

    public BombpassItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}
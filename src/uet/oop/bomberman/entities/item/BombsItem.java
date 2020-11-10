package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class BombsItem extends Item {

    public BombsItem(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_bombs.getFxImage();
    }

    public BombsItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}

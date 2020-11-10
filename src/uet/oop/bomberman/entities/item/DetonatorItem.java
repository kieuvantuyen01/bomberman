package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class DetonatorItem extends Item {
    public DetonatorItem(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_detonator.getFxImage();
    }

    public DetonatorItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

        }

}

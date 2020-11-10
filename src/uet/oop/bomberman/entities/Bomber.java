package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {

    public Bomber(int x, int y) {
        super( x, y);
        this.img= Sprite.player_right.getFxImage();
    }

    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}

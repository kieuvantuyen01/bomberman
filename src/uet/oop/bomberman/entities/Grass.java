package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends StaticEntity {

    public Grass(int x, int y) {
        super(x, y);
        this.img = Sprite.grass.getFxImage();
    }

    public Grass(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}

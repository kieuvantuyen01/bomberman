package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends StaticEntity {

    public Grass() {
        this.img=Sprite.grass.getFxImage();
    }

    public Grass(Coordinates tile) {
        super(tile);
        this.img=Sprite.grass.getFxImage();
    }

    public Grass(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    public void update() {

    }
}

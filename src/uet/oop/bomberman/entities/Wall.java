package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends StaticEntity {

    public Wall() {
        this.img = Sprite.wall.getFxImage();
    }

    public Wall(Coordinates tile) {
        super(tile);
        this.img = Sprite.wall.getFxImage();
    }

    public Wall(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    public void update() {

    }
}

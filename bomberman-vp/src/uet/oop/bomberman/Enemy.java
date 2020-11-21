package uet.oop.bomberman;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovableEntity;

public abstract class Enemy extends MovableEntity {

    public Enemy(Coordinates tile, Image img) {
        super(tile, img);
    }

}

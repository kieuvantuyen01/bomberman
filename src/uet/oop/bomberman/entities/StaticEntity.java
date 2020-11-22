package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;

public abstract class StaticEntity extends Entity {
    public StaticEntity() {
    }

    public StaticEntity(Coordinates tile) {
        super(tile);
    }

    public StaticEntity(Coordinates tile, Image img) {
        super(tile, img);
    }

}

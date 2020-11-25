package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Entity;

public abstract class Item extends Entity {

    public Item() {
    }

    public Item(Coordinates tile) {
        super(tile);
    }

    public Item(Coordinates tile, Image img) {
        super(tile, img);
    }
}

package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.StaticEntity;

public abstract class Item extends StaticEntity {

    public Item(Coordinates tile) {
        super(tile);
    }

    public abstract void getItem();
}

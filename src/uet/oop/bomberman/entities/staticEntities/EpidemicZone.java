package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class EpidemicZone extends StaticEntity {
    public EpidemicZone(Coordinates tile) {
        super(tile);
        img= Sprite.bug.getFxImage();
    }
}

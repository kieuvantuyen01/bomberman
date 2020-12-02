package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends StaticEntity {

    public Portal(Coordinates tile) {
        super(tile);
        this.img=Sprite.portal.getFxImage();
    }

}

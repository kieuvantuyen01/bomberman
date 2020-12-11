package uet.oop.bomberman.entities.staticEntities.item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class BombsItem extends Item {

    public BombsItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_bombs.getFxImage();
    }

    @Override
    public void getItem() {
        BombermanGame.getBomber().addBomb();
        BombermanGame.removeItem(this);
    }

}

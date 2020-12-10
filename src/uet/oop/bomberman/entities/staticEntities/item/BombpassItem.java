package uet.oop.bomberman.entities.staticEntities.item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.Graphics.Sprite;

public class BombpassItem extends Item {

    public BombpassItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_bombpass.getFxImage();
    }

    @Override
    public void getItem() {
        BombermanGame.getBomber().set_bombpass(true);
        BombermanGame.removeItem(this);
    }

}

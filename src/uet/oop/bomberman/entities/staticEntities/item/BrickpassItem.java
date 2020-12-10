package uet.oop.bomberman.entities.staticEntities.item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.Graphics.Sprite;

public class BrickpassItem extends Item {

    public BrickpassItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_wallpass.getFxImage();
    }

    @Override
    public void getItem() {
        BombermanGame.getBomber().set_brickpass(true);
        BombermanGame.removeItem(this);
    }

}

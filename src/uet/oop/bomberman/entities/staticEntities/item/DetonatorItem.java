package uet.oop.bomberman.entities.staticEntities.item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.Graphics.Sprite;

public class DetonatorItem extends Item {

    public DetonatorItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_detonator.getFxImage();
    }

    @Override
    public void getItem() {
        BombermanGame.removeItem(this);
    }

}

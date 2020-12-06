package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

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

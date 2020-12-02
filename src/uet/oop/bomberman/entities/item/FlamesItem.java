package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.graphics.Sprite;

public class FlamesItem extends Item {

    public FlamesItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_flames.getFxImage();
    }

    @Override
    public void getItem() {
        Bomb.setDamage(Bomb.getDamage()+1);
        BombermanGame.removeItem(this);
    }

}

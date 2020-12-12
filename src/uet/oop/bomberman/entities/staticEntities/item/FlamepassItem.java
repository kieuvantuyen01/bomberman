package uet.oop.bomberman.entities.staticEntities.item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class FlamepassItem extends Item {

    public FlamepassItem(Coordinates tile) {
        super(tile);
        this.img = Sprite.powerup_flamepass.getFxImage();
    }

    @Override
    public void getItem() {
        BombermanGame.getBomber().set_flamepass(true);
        BombermanGame.removeItem(this);
    }

}

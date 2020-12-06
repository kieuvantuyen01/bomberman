package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {

    public SpeedItem(Coordinates tile) {
        super(tile);
        this.img = Sprite.powerup_speed.getFxImage();
    }

    @Override
    public void getItem() {
        if (BombermanGame.getBomber().getD().getX() % 2 == 0
                && BombermanGame.getBomber().getD().getY() % 2 == 0) {
            Bomber.setSpeed(2);
            BombermanGame.removeItem(this);
        }
    }

}

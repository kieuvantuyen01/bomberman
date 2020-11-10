package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Speed extends Entity {

    public Speed(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_speed.getFxImage();
    }

    @Override
    public void update() {

    }
}

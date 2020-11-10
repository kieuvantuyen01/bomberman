package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Flamepass extends Entity {

    public Flamepass(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_flamepass.getFxImage();
    }

    @Override
    public void update() {

    }
}

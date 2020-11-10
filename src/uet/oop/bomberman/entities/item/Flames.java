package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Flames extends Entity {

    public Flames(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_flames.getFxImage();
    }

    @Override
    public void update() {

    }
}

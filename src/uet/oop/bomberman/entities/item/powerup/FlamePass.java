package uet.oop.bomberman.entities.item.powerup;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class FlamePass extends Entity {

    public FlamePass(int x, int y) {
        super(x, y);
        this.img = Sprite.wall.getFxImage();
    }

    @Override
    public void update() {

    }
}

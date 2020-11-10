package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bombs extends Entity {

    public Bombs(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_bombs.getFxImage();
    }

    @Override
    public void update() {

    }
}

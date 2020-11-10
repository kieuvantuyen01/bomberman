package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bombpass extends Entity {

    public Bombpass(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_bombpass.getFxImage();
    }

    @Override
    public void update() {

    }
}

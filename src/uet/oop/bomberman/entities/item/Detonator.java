package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Detonator extends Entity {
    public Detonator(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_detonator.getFxImage();
    }

    @Override
    public void update() {

        }

}

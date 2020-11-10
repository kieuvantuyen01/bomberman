package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Wallpass extends Entity {

    public Wallpass(int x, int y) {
        super(x, y);
        this.img = Sprite.powerup_wallpass.getFxImage();
    }

    @Override
    public void update() {

    }
}

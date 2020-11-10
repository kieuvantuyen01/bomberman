package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {

    public Bomber(int x, int y) {
        super( x, y);
        this.img= Sprite.player_right.getFxImage();
    }

    @Override
    public void update() {

    }
}

package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {

    public Brick(int x, int y) {
        super(x, y);
        this.img = Sprite.wall.getFxImage();
    }

    @Override
    public void update() {

    }
}

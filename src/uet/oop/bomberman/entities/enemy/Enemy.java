package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.MovableEntity;

import java.awt.*;

public abstract class Enemy extends MovableEntity {

    public Enemy() {
    }

    public Enemy(Coordinates tile) {
        super(tile);
    }

    public Enemy(Coordinates tile, Image img) {
        super(tile, img);
    }

    public void animate() {
        if (_animate > 120) _animate = 0;
        else _animate++;
    }

    @Override
    public void update() {
        animate();
        if (_alive == false) {
            afterDie();
            if (_animate == 60) {
                BombermanGame.removeEnemy(this);
            }
        }

    }

    @Override
    protected abstract void handleMove();

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }


    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }
}

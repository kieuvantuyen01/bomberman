package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
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

    @Override
    public void update() {

    }

    @Override
    protected abstract void handleMove();

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    public void die() {

    }

    @Override
    protected void afterDie() {

    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }
}

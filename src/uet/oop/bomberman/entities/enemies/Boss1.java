package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Boss1 extends Boss {
    private static Random random = new Random();

    public Boss1(Coordinates tile) {
        super(tile, 1000);
        img = new Image("textures/boss_down1.png");
        tiles.add(tile);
        tiles.add(new Coordinates(tile.getX() + 1, tile.getY()));
        tiles.add(new Coordinates(tile.getX(), tile.getY() + 1));
        tiles.add(new Coordinates(tile.getX() + 1, tile.getY() + 1));
        xa = -speed;
    }

    @Override
    protected void handleDirection() {
        int xt=0, yt=0;
        if (d.getX() == 0 && d.getY() == 0) {
            boolean temp = random.nextBoolean();

            if (temp) {
                do {
                    xt = random.nextInt(3);
                    xt--;
                } while (xt == 0);
            } else {
                do {
                    yt = random.nextInt(3);
                    yt--;
                } while (yt == 0);
            }

            if (xt == -1 && canMoveToDirection(-1, 0) && canMoveToDirection(-1, 1)){
                ya=0;
                xa=-speed;
                d.setX(-Sprite.SCALED_SIZE);
            }
            if (xt==1&&canMoveToDirection(2, 0) && canMoveToDirection(2, 1)){
                ya=0;
                xa=speed;
                d.setX(Sprite.SCALED_SIZE);
            }
            if (yt==-1 && canMoveToDirection(0, -1) && canMoveToDirection(1, -1)){
                xa=0;
                ya=-speed;
                d.setY(-Sprite.SCALED_SIZE);
            }
            if (yt==1 &&canMoveToDirection(0, 2) && canMoveToDirection(1, 2)){
                xa=0;
                ya=speed;
                d.setY(Sprite.SCALED_SIZE);
            }
        }


        handleMove();

    }

    @Override
    public void update() {
        super.update();
        if (!_alive) {
            return;
        }
        proliferate();

        handleDirection();

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
        tiles.removeAll(tiles);
        tiles.add(tile);
        tiles.add(new Coordinates(tile.getX() + 1, tile.getY()));
        tiles.add(new Coordinates(tile.getX(), tile.getY() + 1));
        tiles.add(new Coordinates(tile.getX() + 1, tile.getY() + 1));
    }

    private void proliferate() {
        if (_animate % 120 == 0) {
            BombermanGame.setEnemy(new Oneal(tile));
        }
    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }

    @Override
    protected void handleCollision() {

    }

    @Override
    protected void afterDie() {
    }
}

package uet.oop.bomberman.entities.enemies;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.gameManagement.GameSound;
import uet.oop.bomberman.entities.MovableEntity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends MovableEntity {
    protected int _points;

    public Enemy(Coordinates tile, int points) {
        super(tile);
        this._points = points;
    }

    public void animate() {
        if (_animate > 1200) _animate = 0;
        else _animate++;
    }

    @Override
    public void update() {
        animate();
        if (!_alive) {
            BombermanGame.removeEnemy(this);
            afterDie();

            if (_animate == 60) {
                BombermanGame.addPoints(_points);
                BombermanGame.removeDead(this);
                System.out.println(BombermanGame.get_points());
                GameSound.playMusic(GameSound.ENEMY_DIE);
            }
        }

    }

    protected void handleMove() {
        if (d.getX() != 0 || d.getY() != 0) {
            move(xa * Sprite.PLAYERSPEED, ya * Sprite.PLAYERSPEED);
            d.setX((int) (d.getX() - xa * Sprite.PLAYERSPEED));
            d.setY((int) (d.getY() - ya * Sprite.PLAYERSPEED));
            _moving = true;
        } else {
            _moving = false;
        }
    }

    @Override
    protected abstract void handleDirection();

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }


    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }
}

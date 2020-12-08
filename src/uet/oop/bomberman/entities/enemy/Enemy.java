package uet.oop.bomberman.entities.enemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.gameManagement.GameSound;
import uet.oop.bomberman.entities.MovableEntities;

public abstract class Enemy extends MovableEntities {
    protected int _points;
    BombermanGame game;

    public Enemy(Coordinates tile, int points) {
        super(tile);
        this._points = points;
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
                BombermanGame.addPoints(_points);
                //System.out.println(BombermanGame.get_points());
                BombermanGame.removeEnemy(this);
                GameSound.playMusic(GameSound.ENEMY_DIE);
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

package uet.oop.bomberman.ai;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.MovableEntity;

public class AIMedium extends AI {
    Bomber _bomber;
    MovableEntity _enemy;

    public AIMedium(Bomber _bomber, MovableEntity _enemy) {
        this._bomber = _bomber;
        this._enemy = _enemy;
    }

    @Override
    public int calculateDirection() {
        int rowOrCol = random.nextInt(2);
        if (rowOrCol == 0) {
            if (rightOrLeft() != 10) {
                return rightOrLeft();
            }
            return upOrDown();
        } else {
            if (upOrDown() != 10) {
                return upOrDown();
            }
            return rightOrLeft();
        }
    }

    private int upOrDown() {
        if (_bomber.tile.getX() < _enemy.tile.getX()) {
            return 3;
        } else if (_bomber.tile.getX() > _enemy.tile.getX()) {
            return 1;
        }
        return 10;
    }

    private int rightOrLeft() {
        if (_bomber.tile.getY() < _enemy.tile.getY()) {
            return 0;
        } else if (_bomber.tile.getY() > _enemy.tile.getY()) {
            return 2;
        }
        return 10;
    }
}

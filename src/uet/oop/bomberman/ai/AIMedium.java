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
    public MovableEntity.DIRECTION calculateDirection() {
        int rowOrCol = random.nextInt(2);
        if (rowOrCol == 0) {
            if (rightOrLeft() != MovableEntity.DIRECTION.NONE) {
                return rightOrLeft();
            }
            return upOrDown();
        } else {
            if (upOrDown() != MovableEntity.DIRECTION.NONE) {
                return upOrDown();
            }
            return rightOrLeft();
        }
    }

    private MovableEntity.DIRECTION upOrDown() {
        if (_bomber.tile.getX() < _enemy.tile.getX()) {
            return MovableEntity.DIRECTION.LEFT;
        } else if (_bomber.tile.getX() > _enemy.tile.getX()) {
            return MovableEntity.DIRECTION.RIGHT;
        }
        return MovableEntity.DIRECTION.NONE;
    }

    private MovableEntity.DIRECTION rightOrLeft() {
        if (_bomber.tile.getY() < _enemy.tile.getY()) {
            return MovableEntity.DIRECTION.UP;
        } else if (_bomber.tile.getY() > _enemy.tile.getY()) {
            return MovableEntity.DIRECTION.DOWN;
        }
        return MovableEntity.DIRECTION.NONE;
    }
}

package uet.oop.bomberman.ai;

import uet.oop.bomberman.entities.MovableEntity;

public class AILow extends AI {
    @Override
    public MovableEntity.DIRECTION calculateDirection() {
        int direction = random.nextInt(4);
        if (direction == 0) {
            return MovableEntity.DIRECTION.UP;
        }
        if (direction == 1) {
            return MovableEntity.DIRECTION.RIGHT;
        }
        if (direction == 2) {
            return MovableEntity.DIRECTION.DOWN;
        }
        if (direction == 3) {
            return MovableEntity.DIRECTION.LEFT;
        }
        return MovableEntity.DIRECTION.NONE;
    }
}

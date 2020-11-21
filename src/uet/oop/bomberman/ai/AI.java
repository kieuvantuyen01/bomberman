package uet.oop.bomberman.ai;

import uet.oop.bomberman.entities.MovableEntity;

import java.util.Random;

public abstract class AI {
    protected Random random = new Random();

    public abstract MovableEntity.DIRECTION calculateDirection();
}

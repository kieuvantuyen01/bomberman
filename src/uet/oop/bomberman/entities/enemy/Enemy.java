package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.ai.AI;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.MovableEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Enemy extends MovableEntity {

    protected int _points;
    public int _timeAfter = 40;
    protected int _finalAnimation = 30;
    protected double _speed;
    protected AI _ai;

    protected double MAX_STEPS;
    protected double rest;
    protected double _steps;
    protected Sprite _deadSprite;

    public Enemy(Coordinates tile, int _points, double _speed, Sprite _deadSprite) {
        super(tile);
        this._points = _points;
        this._speed = _speed;
        this._deadSprite = _deadSprite;
        MAX_STEPS = 16 / _speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        _steps = MAX_STEPS;
    }

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
        animate();

        if (!_alive) {
            afterKill();
            return;
        }

        if (_alive) {
            calculateMove();
        }
    }

    protected void afterKill() {
        if (_timeAfter > 0) {
            --_timeAfter;
        } else {
            if (_finalAnimation > 0) {
                --_finalAnimation;
            } else {
                remove();
            }
        }
    }

    public void calculateMove() {
        // TODO: Tính toán hướng đi và di chuyển Enemy theo _ai và cập nhật giá trị cho _direction
        // TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không
        // TODO: sử dụng move() để di chuyển
        // TODO: nhớ cập nhật lại giá trị cờ _moving khi thay đổi trạng thái di chuyển
        int xa = 0, ya = 0;
        if(_steps <= 0){
            _direction = _ai.calculateDirection();
            _steps = MAX_STEPS;
        }

        if(_direction == DIRECTION.UP) {
            ya--;
        }
        if(_direction == DIRECTION.DOWN) {
            ya++;
        }
        if(_direction == DIRECTION.LEFT) {
            xa--;
        }
        if(_direction == DIRECTION.RIGHT) {
            xa++;
        }

        if(canMoveToDirection(xa, ya)) {
            _steps -= 1 + rest;
            move(xa * _speed, ya * _speed);
            _moving = true;
        } else {
            _steps = 0;
            _moving = false;
        }
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

package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Flame extends Entity {
    protected List<FlameSegment> _flameSegments;
    protected int _radius;

    public Flame(Coordinates tile, int radius) {
        super(tile);
        createFlameSegments();
        _radius = radius;
        createFlameSegments();
    }

    @Override
    public void update() {
        _flameSegments.forEach(FlameSegment::animate);
        _flameSegments.forEach(FlameSegment::update);
    }

    public void createSpark(DIRECTION direction) {
        int x = tile.getX();
        int y = tile.getY();
        int radius = _radius;
        int xt = x;
        int yt = y;
        for (int i = 1; i <= radius; i++) {
            switch (direction) {
                case UP:
                    xt = x ;
                    yt = y-i;
                    break;
                case DOWN:
                    xt = x ;
                    yt = y+i;
                    break;
                case LEFT:
                    xt = x-1;
                    yt = y;
                    break;
                case RIGHT:
                    xt = x+1;
                    yt = y;
                    break;
            }
            Entity entity = BombermanGame.getEntityAt(xt, yt);
            if (entity instanceof Wall) {
                radius = i - 1;
            } else if (entity instanceof Brick) {
                radius = i;
                _flameSegments.add(new FlameSegment(new Coordinates(xt, yt), direction, false));
            } else {
                if (i == radius) {
                    _flameSegments.add(new FlameSegment(new Coordinates(xt, yt), direction, true));
                } else {
                    _flameSegments.add(new FlameSegment(new Coordinates(xt, yt), direction, false));
                }
            }
        }
    }

    public void createFlameSegments() {
        int x = tile.getX();
        int y = tile.getY();
        _flameSegments = new ArrayList<>();
        _flameSegments.add(new FlameSegment(new Coordinates(x, y), DIRECTION.CENTER, false));
        createSpark(DIRECTION.UP);
        createSpark(DIRECTION.RIGHT);
        createSpark(DIRECTION.DOWN);
        createSpark(DIRECTION.LEFT);
    }

    public List<FlameSegment> get_flameSegments() {
        return _flameSegments;
    }

    public void set_flameSegments(List<FlameSegment> _flameSegments) {
        this._flameSegments = _flameSegments;
    }
}

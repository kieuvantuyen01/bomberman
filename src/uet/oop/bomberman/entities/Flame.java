package uet.oop.bomberman.entities;

import uet.oop.bomberman.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Flame extends Entity {
    public FlameSegment[] _flameSegments;
    protected int _direction;
    protected int _radius;

    public Flame(Coordinates tile, int direction, int radius, boolean last) {
        super(tile);
        createFlameSegments();
        _direction = direction;
        _radius = radius;
        createFlameSegments();
    }

    @Override
    public void update() {

    }

    public void createFlameSegments() {
        int x = tile.getX();
        int y = tile.getY();
        _flameSegments = new FlameSegment[4];
        boolean last = false;
        for (int i = 0; i < _flameSegments.length; i++) {
            if (i == _flameSegments.length - 1)
                last = true;
            switch (_direction) {
                case 0:
                    y--;
                    break;
                case 1:
                    x++;
                    break;
                case 2:
                    y++;
                    break;
                case 3:
                    x--;
                    break;
            }

            _flameSegments[i] = new FlameSegment(new Coordinates(x, y), _direction, last);
        }
    }
}

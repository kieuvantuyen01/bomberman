package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class FlameSegment extends Entity {
    protected boolean _last;
    protected int _animate = 0;

    public FlameSegment(Coordinates tile, MovableEntity.DIRECTION direction, boolean last) {
        super(tile);
        _last = last;
        this._direction = direction;
    }


    public void animate() {
        if (_animate > 90) _animate = 0;
        else _animate++;
    }

    protected void chooseSprite() {
        switch (_direction) {
            case CENTER:
                loadAnimated(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2);
                break;
            case UP:
                if (_last) {
                    loadAnimated(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2);
                } else {
                    loadAnimated(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2);
                }
                break;
            case RIGHT:
                if (_last) {
                    loadAnimated(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2);
                } else {
                    loadAnimated(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2);
                }
                break;
            case DOWN:
                if (_last) {
                    loadAnimated(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2);
                } else {
                    loadAnimated(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2);
                }
                break;
            case LEFT:
                if (_last) {
                    loadAnimated(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2);
                } else {
                    loadAnimated(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2);
                }
                break;
            case NONE:
                break;
            default:
                break;
        }
    }


    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        img = Sprite.movingSprite(sprite1.getFxImage(), sprite2.getFxImage(), sprite3.getFxImage(), _animate, 30);
    }

    @Override
    public void update() {
        animate();
        chooseSprite();
    }


    public boolean collide(Entity e) {
        // TODO: xử lý khi FlameSegment va chạm với MovableEntity
        if (e instanceof MovableEntity) {
            ((MovableEntity) e).die();
        }
        return true;
    }
}

package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.gameManagement.GameSound;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

public class FlameSegment extends StaticEntity implements Animated {
    protected boolean _last;
    protected int _animate = -1;

    public FlameSegment(Coordinates tile, Entity.DIRECTION direction, boolean last) {
        super(tile);
        _last = last;
        this._direction = direction;
    }


    public void animate() {
        if (_animate > 30) _animate = 0;
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
            default:
                break;
        }
    }


    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        img = Sprite.movingSprite(sprite2.getFxImage(), sprite3.getFxImage(), sprite1.getFxImage(), _animate, 10);
    }

    @Override
    public void update() {
        animate();
        chooseSprite();
        handleCollision();
    }


    public void handleCollision() {
        Entity entity = BombermanGame.getEntityAt(tile.getX(), tile.getY());
        if (entity instanceof MovableEntities) {
            if (!((MovableEntities) entity).is_flamepass()) {
                if (entity instanceof Bomber && ((Bomber) entity).is_alive()) {
                    Bomber.bomber_life--;
                    GameSound.playMusic(GameSound.BOMBER_DIE);
                }
                ((MovableEntities) entity).die();
            }
        }
        if (entity instanceof Brick) {
            ((Brick) entity).remove();
        }
    }
}

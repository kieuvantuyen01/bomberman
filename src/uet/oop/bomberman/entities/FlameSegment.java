package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class FlameSegment extends Entity {
    protected boolean _last;
    protected int _animate = 0;

    public FlameSegment(Coordinates tile, int direction, boolean last) {
        super(tile);
        _last = last;
        switch (direction) {
            case 0:
                if(!last) {
                    img = Sprite.explosion_vertical2.getFxImage();
                } else {
                    img = Sprite.explosion_vertical_top_last2.getFxImage();
                }
                break;
            case 1:
                if(!last) {
                    img = Sprite.explosion_horizontal2.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal_right_last2.getFxImage();
                }
                break;
            case 2:
                if(!last) {
                    img = Sprite.explosion_vertical2.getFxImage();
                } else {
                    img = Sprite.explosion_vertical_down_last2.getFxImage();
                }
                break;
            case 3:
                if(!last) {
                    img = Sprite.explosion_horizontal2.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal_left_last2.getFxImage();
                }
                break;
        }
    }


    public void animate() {
        if (_animate > 90) _animate = 0;
        else _animate++;
    }


    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        img = Sprite.movingSprite(sprite1.getFxImage(), sprite2.getFxImage(), sprite3.getFxImage(), _animate, 30);
    }

    @Override
    public void update() {
        //animate();
        //loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3);
    }


    public boolean collide(Entity e) {
        // TODO: xử lý khi FlameSegment va chạm với MovableEntity
        if(e instanceof MovableEntity){
            ((MovableEntity) e).die();
        }
        return true;
    }
}

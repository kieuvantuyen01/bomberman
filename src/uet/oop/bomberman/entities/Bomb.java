package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

import javax.xml.parsers.SAXParser;
import java.nio.channels.spi.SelectorProvider;

public class Bomb extends StaticEntity implements Animated {
    protected double _timeToExplode = 120;
    protected boolean _exploded = false;
    protected int _animate = 0;
    protected BombermanGame game = new BombermanGame();

    public Bomb() {
    }

    public Bomb(Coordinates tile) {
        super(tile);
        img= Sprite.bomb.getFxImage();

    }
    public void bombExplode() {
        if (!_exploded) {
            if (_timeToExplode > 0) {
                _timeToExplode--;
            } else {
                _exploded = true;
                removed();
                game.removeBomb();
            }
        }
    }

    public void reset() {
        if(is_removed()){
            _timeToExplode = 120;
            _exploded = false;
            _removed = false;
        }
    }
    public Bomb(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    public void animate() {
        if (_animate > 90) _animate = 0;
        else _animate++;
    }

    @Override
    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        img=Sprite.movingSprite(sprite1.getFxImage(),sprite2.getFxImage(),sprite3.getFxImage(),_animate,30);
    }

    @Override
    public void update() {
        animate();
        bombExplode();
        loadAnimated(Sprite.bomb,Sprite.bomb_1,Sprite.bomb_2);
    }
}

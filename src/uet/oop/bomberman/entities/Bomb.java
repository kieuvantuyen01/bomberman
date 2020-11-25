package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;


public class Bomb extends StaticEntity implements Animated {
    protected double _timeToExplode = 120;
    protected int _animate = 0;
    protected static int damage = 1;

    public Bomb(Coordinates tile) {
        super(tile);

    }

    @Override
    public void animate() {
        if (_animate > 90) _animate = 0;
        else _animate++;
        _timeToExplode--;
    }

    @Override
    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        img = Sprite.movingSprite(sprite1.getFxImage(), sprite2.getFxImage(), sprite3.getFxImage(), _animate, 30);
    }

    @Override
    public void update() {
        animate();
        if (_timeToExplode < 0){
            bombExplode();
            return;
        }
        loadAnimated(Sprite.bomb,Sprite.bomb_1,Sprite.bomb_2);

    }

    protected void bombExplode(){
        BombermanGame.setFlame(new Flame(tile,damage));
        BombermanGame.removeBomb();
    }
}

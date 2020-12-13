package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Animated;
import uet.oop.bomberman.graphics.Sprite;

public class EpidemicZone extends StaticEntity implements Animated {
    private  int _animate=0;
    private boolean _activate=false;
    public EpidemicZone(Coordinates tile) {
        super(tile);
        img= Sprite.bug.getFxImage();
    }

    @Override
    public void animate() {
        if (_animate>1000) {
            _animate=0;
        }
        _animate++;
    }

    @Override
    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {

    }

    @Override
    public void update() {
        animate();
    }
}

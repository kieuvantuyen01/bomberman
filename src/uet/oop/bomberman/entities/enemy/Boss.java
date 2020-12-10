package uet.oop.bomberman.entities.enemy;

import uet.oop.bomberman.Coordinates;
import java.util.ArrayList;
import java.util.List;

public abstract class Boss extends Enemy {
    protected List<Coordinates> tiles;

    public Boss(Coordinates tile, int points) {
        super(tile, points);
        tiles=new ArrayList<>();
        pixel=tile.convertTileToPixel();
        //img= Sprite.boss_down.getFxImage();
    }

    public List<Coordinates> getTiles() {
        return tiles;
    }
}

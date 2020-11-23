package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class FlamepassItem extends Item {

    public FlamepassItem() {
        this.img=Sprite.powerup_flamepass.getFxImage();
    }

    public FlamepassItem(Coordinates tile) {
        super(tile);
        this.img=Sprite.powerup_flamepass.getFxImage();
    }

    public FlamepassItem(Coordinates tile, Image img) {
        super(tile, img);
    }

    @Override
    public void update() {}

    public boolean collide(Entity e) {return false;}
}

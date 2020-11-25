package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;

public abstract class Entity {
    //T?a ?? X t�nh t? g�c tr�i tr�n trong Canvas
    protected Coordinates pixel;

    protected Coordinates tile;

    protected Image img;

    protected DIRECTION _direction = DIRECTION.NONE;

    enum DIRECTION {
        NONE, UP, RIGHT, DOWN, LEFT, CENTER
    }

    public Entity(Coordinates tile) {
        this.tile = tile;
        pixel = tile.convertTileToPixel();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, pixel.getX(), pixel.getY());
    }

    public Coordinates getTile() {
        return tile;
    }

    public void setTile(Coordinates tile) {
        this.tile = tile;
    }

    public Coordinates getPixel() {
        return pixel;
    }

    public void setPixel(Coordinates pixel) {
        this.pixel = pixel;
    }

}
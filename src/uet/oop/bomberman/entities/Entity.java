package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Entity {
    //T?a ?? X t�nh t? g�c tr�i tr�n trong Canvas
    protected Coordinates pixel;

    public Coordinates tile;

    protected Image img;

    protected Sprite _sprite;

    protected Rectangle rectangle;

    protected String name;
    private boolean _removed = false;

    public void remove() {
        _removed = true;
    }

    public boolean isRemoved() {
        return _removed;
    }

    public Entity() {

    }
    //Kh?i t?o ??i t??ng, chuy?n t? t?a ?? ??n v? sang t?a ?? trong canvas


    public Entity(Coordinates tile) {
        this.tile = tile;
        pixel = tile.convertTileToPixel();
    }

    public Entity(Coordinates tile, Image img) {
        this.tile = tile;
        pixel = tile.convertTileToPixel();
        this.img = img;
        this.rectangle = new Rectangle(pixel.getX(), pixel.getY(), (int) img.getWidth(), (int) img.getHeight());
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, pixel.getX(), pixel.getY());
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public abstract void update();

    public boolean isCollision(Entity other) {
        if (this.tile.getX() != other.tile.getX()
                || this.tile.getY() != other.tile.getY()) {
            return false;
        }
        return true;
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
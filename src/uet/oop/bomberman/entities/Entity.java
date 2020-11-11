package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Entity {
    //T?a ?? X tính t? góc trái trên trong Canvas
    protected int x;

    //T?a ?? Y tính t? góc trái trên trong Canvas
    protected int y;

    protected Image img;

    protected Sprite _sprite;

    protected Rectangle rectangle;

    protected String name;

    public Entity() {

    }
    //Kh?i t?o ??i t??ng, chuy?n t? t?a ?? ??n v? sang t?a ?? trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.rectangle = new Rectangle(x ,y , (int) img.getWidth(), (int) img.getHeight());
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public abstract void update();
}

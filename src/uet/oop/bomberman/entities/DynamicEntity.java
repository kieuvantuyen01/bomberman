package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class DynamicEntity extends Entity {
    public DynamicEntity(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    public DynamicEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}

package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.graphics.Sprite;

import java.util.*;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = (int) (31);
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;

    private static List<Entity> entities = new ArrayList<>();
    private static Bomber bomber;
    private static List<Entity> bombs = new ArrayList<>();
    private static List<Entity> walls = new ArrayList<>();
    private static List<Entity> portals = new ArrayList<>();
    private static List<Entity> bricks = new ArrayList<>();
    private static List<Entity> items = new ArrayList<>();
    private static List<Entity> enemies = new ArrayList<>();
    private static List<Grass> grasses = new ArrayList<>();
    public static Keyboard input = new Keyboard();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                input.keyPressed(event);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                input.keyRelease(event);
            }
        });
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();
    }

    public void createMap() {
        LevelLoader.getInstance().loadMap(1);
    }

    public static Bomber getBomber() {
        return bomber;
    }

    public static void setBomber(Bomber bomber) {
        BombermanGame.bomber = bomber;
    }

    public void update() {
        enemies.forEach(Entity::update);
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i) != null) bombs.get(i).update();
        }
        if (bomber != null) {
            bomber.update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grasses.forEach(grass -> grass.render(gc));
        walls.forEach(wall -> wall.render(gc));
        items.forEach(item -> item.render(gc));
        bricks.forEach(brick -> brick.render(gc));
        enemies.forEach(enemy -> enemy.render(gc));
        bombs.forEach(g -> g.render(gc));
        if (bomber != null) {
            bomber.render(gc);
        }
    }

    public static List<Entity> getBombs() {
        return bombs;
    }

    public static List<Entity> getWalls() {
        return walls;
    }

    public static void setWall(Wall wall) {
        BombermanGame.walls.add(wall);
    }

    public static List<Entity> getBricks() {
        return bricks;
    }

    public static void setBrick(Brick brick) {
        BombermanGame.bricks.add(brick);
    }

    public static List<Entity> getPortals() {
        return portals;
    }

    public static void setPortal(Portal portal) {
        BombermanGame.portals.add(portal);
    }

    public static List<Entity> getItems() {
        return items;
    }

    public static void setItem(Item item) {
        BombermanGame.items.add(item);
    }

    public static List<Entity> getEnemies() {
        return enemies;
    }

    public static void setEnemy(Enemy enemy) {
        BombermanGame.enemies.add(enemy);
    }

    public static List<Grass> getGrasses() {
        return grasses;
    }

    public static void setGrass(Grass grass) {
        BombermanGame.grasses.add(grass);
    }

    public static void setBomb(Bomb bomb) {
        BombermanGame.bombs.add(bomb);
    }

    public static void removeBomb() {
        bombs.remove(0);
        bomber.addBomb();
    }

    public static void setEntity(Entity entity) {
        entities.add(entity);
    }

    public static Entity getEntityAt(int x, int y) {
        Entity entity = null;
        entity = get(walls, x, y);
        if (entity != null) {
            return entity;
        }
        entity = get(bricks, x, y);
        if (entity != null) {
            return entity;
        }
        entity = get(portals, x, y);
        if (entity != null) {
            return entity;
        }
        entity = get(items, x, y);
        if (entity != null) {
            return entity;
        }
        entity = get(enemies, x, y);
        return entity;
    }

    public static Entity get(List<Entity> entities, int x, int y) {
        Coordinates coordinates = new Coordinates(x, y);
        Iterator<Entity> itr = entities.iterator();
        Entity cur;
        Entity entity = null;
        while (itr.hasNext()) {
            cur = itr.next();
            if (cur.getTile().getY() == coordinates.getY() && cur.getTile().getX() == coordinates.getX()) {
                entity = cur;
            }
        }
        return entity;
    }

    public static void removeBomber() {
        bomber = null;
    }

    public static void removeItem(Item item) {
        items.remove(item);
    }
}

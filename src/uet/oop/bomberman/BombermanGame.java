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
import java.util.concurrent.CopyOnWriteArrayList;

public class BombermanGame extends Application {

    public static final int WIDTH = (int) (31);
    public static final int HEIGHT = 13;
    public static int load_map_level = 1;
    private GraphicsContext gc;
    private Canvas canvas;

    private static List<Entity> entities = new ArrayList<>();
    private static Bomber bomber;
    private static List<Flame> flames = new CopyOnWriteArrayList<>();
    private static List<Entity> bombs = new CopyOnWriteArrayList<>();
    private static List<Entity> walls = new ArrayList<>();
    private static List<Entity> portals = new ArrayList<>();
    private static List<Entity> bricks = new CopyOnWriteArrayList<>();
    private static List<Entity> items = new CopyOnWriteArrayList<>();
    private static List<Entity> enemies = new CopyOnWriteArrayList<>();
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

        createMap(1);
    }

    public static void createMap(int level) {
        LevelLoader.getInstance().loadMap(level);
    }

    public static Bomber getBomber() {
        return bomber;
    }

    public static void setBomber(Bomber bomber) {
        BombermanGame.bomber = bomber;
    }

    public void update() {
        flames.forEach(Flame::update);
        for (Entity _brick : bricks) {
            Brick brick = (Brick) _brick;
            brick.update();
        }
        for (Entity _enemy : enemies) {
            MovableEntity enemy = (MovableEntity) _enemy;
            enemy.update();
        }

        for (Entity _bomb : bombs) {
            Bomb bomb = (Bomb) _bomb;
            bomb.update();
        }

        if (bomber != null) {
            bomber.update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grasses.forEach(grass -> grass.render(gc));
        walls.forEach(wall -> wall.render(gc));
        portals.forEach(portal -> portal.render(gc));
        items.forEach(item -> item.render(gc));
        for (Flame flame : flames) {
            flame.get_flameSegments().forEach(flameSegment -> flameSegment.render(gc));
        }
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
        if (bomber!=null){
            bomber.addBomb();
        }
    }

    public static void setEntity(Entity entity) {
        entities.add(entity);
    }

    public static Entity getEntityAt(int x, int y) {
        Entity entity;
        entity = get(walls, x, y);
        if (entity != null) {
            return entity;
        }
        entity = get(bricks, x, y);
        if (entity != null) {
            return entity;
        }

        entity = get(bombs, x, y);
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
        if (entity!=null){
            return entity;
        }
        if (bomber != null
                && bomber.getTile().getX() == x
                && bomber.getTile().getY() == y) {
            entity = bomber;
        }
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

    public static void setFlame(Flame flame) {
        flames.add(flame);
    }

    public static void removeFlame() {
        flames.remove(0);
    }

    public static void removeBrick(Brick brick) {
        bricks.remove(brick);
    }

    public static void removeEnemy(Enemy enemy){
        enemies.remove(enemy);
    }

    public static void removeOldData(){
        entities.removeAll(entities);
        bomber=null;
        flames.removeAll(flames);
        bombs.removeAll(bombs);
        walls.removeAll(walls);
        portals.removeAll(portals);
        bricks.removeAll(bricks);
        items.removeAll(items);
        enemies.removeAll(enemies);
        grasses.removeAll(grasses);
    }
}

package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovableEntity;
import uet.oop.bomberman.entities.enemies.Boss;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.staticEntities.item.Item;
import uet.oop.bomberman.entities.staticEntities.*;
import uet.oop.bomberman.gameManagement.*;
import uet.oop.bomberman.graphics.Sprite;

import javax.sound.sampled.Clip;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static uet.oop.bomberman.gameManagement.GameSound.loopMusic;

public class BombermanGame extends Application {
    public static Stage stage = new Stage();

    public static final int WIDTH = 34;
    public static final int HEIGHT = 15;
    public static int load_map_level = 1;
    private GraphicsContext gc;
    private Canvas canvas;

    public static boolean isPauseGame = false;

    public static int _points = 0;
    private static Bomber bomber;
    private static List<Entity> entities;
    private static List<Flame> flames;
    private static List<Entity> bombs;
    private static List<Entity> walls;
    private static List<Entity> portals;
    private static List<Entity> bricks;
    private static List<Entity> items;
    private static List<Entity> enemies;
    private static List<Grass> grasses;
    private static List<Enemy> dead;
    public static Keyboard input = new Keyboard();

    public static Clip THREAD_SOUNDTRACK = loopMusic(GameSound.PLAYGAME);

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
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
        if (entity != null) {
            return entity;
        }
        if (bomber != null
                && bomber.getTile().getX() == x
                && bomber.getTile().getY() == y) {
            entity = bomber;
        }
        return entity;
    }

    public static void createMap(int level) {
        initData();
        LevelLoader.getInstance().loadMap(level);
    }

    public void update() {
        Fps.update();
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
        for (Entity _enemy:dead){
            MovableEntity enemy= (MovableEntity) _enemy;
            enemy.update();
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
        dead.forEach(dead->dead.render(gc));
        if (bomber != null) {
            bomber.render(gc);
        }
    }

    public static void initData() {
        entities = new ArrayList<>();
        flames = new CopyOnWriteArrayList<>();
        bombs = new CopyOnWriteArrayList<>();
        walls = new ArrayList<>();
        portals = new ArrayList<>();
        bricks = new CopyOnWriteArrayList<>();
        items = new CopyOnWriteArrayList<>();
        enemies = new CopyOnWriteArrayList<>();
        grasses = new ArrayList<>();
        dead=new CopyOnWriteArrayList<>();
    }

    public static Entity get(List<Entity> entities, int x, int y) {
        Iterator<Entity> itr = entities.iterator();
        Entity cur;
        Entity entity = null;
        while (itr.hasNext()) {
            cur = itr.next();
            if (cur.getTile().getY() == y && cur.getTile().getX() == x) {
                entity = cur;
            }
            if (cur instanceof Boss) {
                Boss boss = (Boss) cur;
                for (Coordinates tile : boss.getTiles()) {
                    if (tile.getX() == x && tile.getY() == y) {
                        entity = boss;
                    }
                }
            }
        }
        return entity;
    }


    @Override
    public void start(Stage stage1) {
        stage = stage1;
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(40);
        gc = canvas.getGraphicsContext2D();

        // Tạo các thông số hiển thị trong game.
        MessageDisplay messageDisplay = new MessageDisplay();
        Background background = new Background(new BackgroundFill(Color.PINK, null, new Insets(0, -1000, -398, 0)));
        //messageDisplay.setBackground(background);
        // Tao root container
        Pane root = new Pane();
        root.getChildren().add(canvas);
        root.getChildren().add(messageDisplay);
        root.setBackground(background);
        // Tao scene
        Scene scene = new Scene(root);
        // Them scene vao stage

        scene.setOnKeyPressed(event -> input.keyPressed(event));

        scene.setOnKeyReleased(event -> input.keyRelease(event));

        // Them scene vao stage
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Bomberman game | " + Fps.get() + " fps");
        Timeline animation;
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> fpsDisplay(stage)));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        Image icon = new Image(getClass().getResourceAsStream("/textures/Bomberman_Icon.png"));
        stage.getIcons().add(icon);
        stage.show();

        initPLayThread();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                if (!isPauseGame) {
                    update();
                }
            }
        };
        timer.start();
        createMap(load_map_level);
        BombermanGame.THREAD_SOUNDTRACK.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void initPLayThread() {
        AnimationTimer playThread = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }

            @Override
            public void start() {
                THREAD_SOUNDTRACK.loop(Clip.LOOP_CONTINUOUSLY);
                super.start();
            }

            @Override
            public void stop() {
                THREAD_SOUNDTRACK.stop();
                super.stop();
            }
        };
    }

    public static void removeBomb() {
        bombs.remove(0);
        if (bomber != null) {
            bomber.addBomb();
        }
    }

    public static int get_points() {
        return _points;
    }

    public static void resetPoint() {
        _points = 0;
    }

    public static void addPoints(int point) {
        _points += point;
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

    public static void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public static void setEntity(Entity entity) {
        entities.add(entity);
    }

    public static Bomber getBomber() {
        return bomber;
    }

    public static void setBomber(Bomber bomber) {
        BombermanGame.bomber = bomber;
    }

    public static List<Enemy> getDead() {
        return dead;
    }

    public static void setDead(Enemy enemy) {
        BombermanGame.dead.add((Enemy) enemy);
    }

    public void fpsDisplay(Stage stage) {
        stage.setTitle("Bomberman game | " + Fps.get() + " fps");
    }

    public static void removeDead(Enemy enemy){
        BombermanGame.dead.remove(enemy);
    }
}

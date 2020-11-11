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
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = (int) (31 / 2);
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    private LevelLoader instance = LevelLoader.getInstance();
    private Keyboard input = new Keyboard();

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
                switch (event.getCode()) {
                    case UP:
                        input.setUp(true);
                        break;
                    case DOWN:
                        input.setDown(true);
                        break;
                    case LEFT:
                        input.setLeft(true);
                        break;
                    case RIGHT:
                        input.setRight(true);
                        break;
                    case SPACE:
                        input.setSpace(true);
                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        input.setUp(false);
                        break;
                    case DOWN:
                        input.setDown(false);
                        break;
                    case LEFT:
                        input.setLeft(false);
                        break;
                    case RIGHT:
                        input.setRight(false);
                        break;
                    case SPACE:
                        input.setSpace(false);
                        break;
                }
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

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(), input);
        //Entity testBalloom = new Balloom(6,6,Sprite.balloom_right1.getFxImage());
        entities.add(bomberman);
        //entities.add(testBalloom);
    }

    public void createMap() {
        Entity[][] map = instance.loadMap(1);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i == 0 || i == HEIGHT - 1 || j == 0 || j == WIDTH - 1) {
                    map[i][j] = new Wall(j, i, Sprite.wall.getFxImage());
                }
                stillObjects.add(map[i][j]);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public static Entity getEntity(Rectangle rec) {
        /*//System.out.println(stillObjects.size());
        for (int i = 0; i < stillObjects.size(); i++) {
            Entity t = stillObjects.get(i);
            if (t instanceof BomBang) {
                if (((BomBang) t).handleCollisionBomBang(rec)) {
                    return t;
                }
            }
            if (t instanceof Bomb) {

            } else {
                if (t.getRectangle().getBounds().intersects(rec.getBounds())) {
                    if (t instanceof Wall ) {
                        return t;
                    }
                    //System.out.println("yes");

                }
            }

        }

        for (int i = 0; i < changeObjects.size(); i++) {
            Entity t = changeObjects.get(i);
            if (t != null ) {
                if (t.getRectangle().getBounds().intersects(rec.getBounds())) {
                    if (t instanceof Brick) {
                        return t;
                    }
                }

            }
        }
*/
        return null;
    }

    //ki?m tra ch?m qu�i
    public static boolean checkCollisionEnemy(Rectangle rec) {
        /*//System.out.println(stillObjects.size());
        for (int i = 0; i < entities.size(); i++) {
            Entity t = entities.get(i);
            if (!(t instanceof Bomber)) {
                if (t.getRectangle().intersects(rec)) {
                    return true;
                }
            }
        }*/
        return false;
    }

    //ki?m tra ch?m item
    public static Entity checkCollisionItem(Rectangle rec) {
        /*
        for (int i = 0; i < changeObjects.size(); i++) {
            Entity t = changeObjects.get(i);
            if (! (t instanceof Brick)) {
                if (t.getRectangle().intersects(rec)) {
                    return t;
                }
            }
        }*/
        return null;
    }
}

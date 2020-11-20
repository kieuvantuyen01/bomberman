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
import uet.oop.bomberman.entities.enemy.Balloom;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.*;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = (int) (31);
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;

    private static List<Entity> entities = new ArrayList<>();
    private static Bomber bomber;
    private static List<Entity> stillObjects = new ArrayList<>();
    private LevelLoader instance = LevelLoader.getInstance();
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
        Stack<Entity>[][] map = instance.loadMap(1);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                while (!map[i][j].empty())
                stillObjects.add(map[i][j].pop());
            }
        }
    }

    public static Bomber getBomber() {
        return bomber;
    }

    public static void setBomber(Bomber bomber) {
        BombermanGame.bomber = bomber;
    }

    public void update() {
        entities.forEach(Entity::update);
        bomber.update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomber.render(gc);
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

    public static void setEntity(Entity entity) {
        entities.add(entity);
    }
    public static Entity getEntityAt(int x,int y){
        Coordinates coordinates=new Coordinates(x,y);
        Iterator<Entity> itr=stillObjects.iterator();
        Entity cur;
        Entity entity = null;
        while (itr.hasNext()){
            cur=itr.next();
            if(cur.getTile().getY()==coordinates.getY()&&cur.getTile().getX()==coordinates.getX()){
                entity=cur;
            }
        }
        return entity;
    }
}

package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import uet.oop.bomberman.gameDisplayHandling.HeartDisplay;
import uet.oop.bomberman.gameDisplayHandling.ScoreDisplay;
import uet.oop.bomberman.gameDisplayHandling.TimeHandling;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.enemy.Boss;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.entities.staticEntities.*;
import uet.oop.bomberman.gameDisplayHandling.LevelDisplay;
import uet.oop.bomberman.graphics.Sprite;

import javax.sound.sampled.Clip;
import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static uet.oop.bomberman.gameDisplayHandling.GameSound.loopPlaySound;

public class BombermanGame extends Application {
    public HashMap<Integer, String> top_high_scores = new HashMap<>();
    public ArrayList<Integer> scores = new ArrayList<>();

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static int load_map_level = 1;
    private GraphicsContext gc;
    private Canvas canvas;

    private static int _points = 0;

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
    private final List<Message> _messages = new ArrayList<>();
    public static Keyboard input = new Keyboard();

    public static Clip THREAD_SOUNDTRACK = loopPlaySound("playgame.wav");

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    public void getScoreChartFromFile() {
        try {
            FileReader fileReader = new FileReader("res\\scores\\scoreChart.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line, name, score;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    name = parts[0];
                    score = parts[1];
                    scores.add(Integer.parseInt(score));
                    top_high_scores.put(Integer.parseInt(score), name);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToScoreChartFile() {
        try {
            FileWriter fos = new FileWriter("res\\scores\\scoreChart.txt");
            BufferedWriter bw = new BufferedWriter(fos);
            for (int score : scores) {
                bw.write(top_high_scores.get(score) + " " + String.valueOf(score) + "\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addIntoTopHighScores() {
        int point = _points;
        String name = "Tùng";
        scores.add(point);
        Comparator c = Collections.reverseOrder();
        Collections.sort(scores, c);
        top_high_scores.put(point, name);
        for (int score : scores) {
            System.out.println(top_high_scores.get(score) + " " + score);
        }
    }

    //Sử dụng hàm này khi số phần tử của mảng scores đạt mức tối đa là 10.
    public void removeFromTopHighScores() {
        scores.remove(scores.size() - 1);
    }

    public void handleScores() {
        getScoreChartFromFile();
        addIntoTopHighScores();
        if (scores.size() > 10) {
            removeFromTopHighScores();
        }
        writeToScoreChartFile();
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
        flames.forEach(Flame::update);
        for (Entity _brick : bricks) {
            Brick brick = (Brick) _brick;
            brick.update();
        }
        for (Entity _enemy : enemies) {
            MovableEntities enemy = (MovableEntities) _enemy;
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
        renderMessages(gc);
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
            if (cur instanceof Boss){
                Boss boss= (Boss) cur;
                for (Coordinates tile:boss.getTiles()){
                    if (tile.getX()==x&&tile.getY()==y){
                        entity=boss;
                    }
                }
            }
        }
        return entity;
    }


    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(40);
        gc = canvas.getGraphicsContext2D();

        // Tao thoi gian dem nguoc
        TimeHandling time_display = new TimeHandling();

        //Tao mang cua nguoi choi
        HeartDisplay heart_label = new HeartDisplay();

        // Tao diem
        ScoreDisplay score_label = new ScoreDisplay();

        // Hien thi level truoc moi man choi
        LevelDisplay level_label = new LevelDisplay();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(time_display);
        root.getChildren().add(heart_label);
        root.getChildren().add(score_label);
        root.getChildren().add(level_label);

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
        stage.setTitle("Bomberman game | 60 rate, 1814 fps");
        Image icon = new Image(getClass().getResourceAsStream("/textures/Bomberman_Icon.png"));
        stage.getIcons().add(icon);
        stage.show();

        initPLayThread();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
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

    public void renderMessages(GraphicsContext g) {
        Message m;
        for (int i = 0; i < _messages.size(); i++) {
            m = _messages.get(i);
            g.setFill(Color.WHITE);
            g.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.SEMI_BOLD, m.getSize()));
            g.fillText(m.getMessage(), m.getPixel().getX() - 2 * 3, m.getPixel().getY());
        }
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
}

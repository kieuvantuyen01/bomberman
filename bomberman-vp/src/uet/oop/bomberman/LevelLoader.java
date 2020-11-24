package uet.oop.bomberman;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.enemy.Balloom;
import uet.oop.bomberman.entities.enemy.Ghost;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.entities.item.FlamesItem;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.entities.item.SpeedItem;
import uet.oop.bomberman.exception.LevelLoaderException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Struct;
import java.util.Stack;

public class LevelLoader {
    public static LevelLoader instance = null;
    public String LEVEL_TEMPLATE = "/levels/Level%d.txt";

    private int level;
    private int rows;
    private int cols;

    private LevelLoader() {

    }

    public static LevelLoader getInstance() {
        if (instance == null) {
            instance = new LevelLoader();
        }
        return instance;
    }

    public void loadMap(int level) {

        try {
            InputStream is = this.getClass().getResourceAsStream(
                    String.format(LEVEL_TEMPLATE, level));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String[] line = br.readLine().split("\\s+");
            if (line.length != 3) {
                throw new LevelLoaderException("Lỗi đọc file: Số hàng, cột");
            }
            this.level = Integer.parseInt(line[0]);
            if (this.level != level) {
                throw new LevelLoaderException("Lỗi đọc file: Sai thông số màn chơi");
            }
            rows = Integer.parseInt(line[1]);
            cols = Integer.parseInt(line[2]);
            System.out.println(rows + " " + cols);
            for (int i = 0; i < rows; i++) {
                String map = br.readLine();
                System.out.println(map);
                for (int j = 0; j < cols; j++) {
                    BombermanGame.setGrass(new Grass(new Coordinates(j,i)));
                    switch (map.charAt(j)) {
                        case '#':
                            BombermanGame.setWall(new Wall(new Coordinates(j,i)));
                            break;
                        case '*':
                            BombermanGame.setBrick(new Brick(new Coordinates(j,i)));
                            break;
                        case 'x':
                            BombermanGame.setPortal(new Portal(new Coordinates(j,i)));
                            break;
                        case 'p':
                            BombermanGame.setBomber(new Bomber(new Coordinates(j,i),BombermanGame.input));
                            break;
                        case 'f':
                            BombermanGame.setItem(new FlamesItem(new Coordinates(j,i)));
                            break;
                        case 's':
                            BombermanGame.setItem(new SpeedItem(new Coordinates(j,i)));
                            break;
                        case '1':
                            BombermanGame.setEnemy(new Balloom(new Coordinates(j,i),true));
                            break;
                        case '2':
                            BombermanGame.setEnemy(new Oneal(new Coordinates(j,i)));
                            break;
                        case '3':
                            BombermanGame.setEnemy(new Ghost(new Coordinates(j,i), true));
                            break;
                        default:
                            break;
                    }
                }
            }
            br.close();
            is.close();

        } catch (LevelLoaderException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public int getLevel() {
        return level;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}

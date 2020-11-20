package uet.oop.bomberman;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.enemy.Balloom;
import uet.oop.bomberman.entities.item.FlamesItem;
import uet.oop.bomberman.entities.item.SpeedItem;
import uet.oop.bomberman.exception.LevelLoaderException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class LevelLoader {
    public static LevelLoader instance = null;
    private Stack<Entity>[][] matrix;
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

    public Stack<Entity>[][] loadMap(int level) {

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
            Stack<Entity>[][] matrix = new Stack[rows][cols];
            for (int i = 0; i < rows; i++) {
                String map = br.readLine();
                System.out.println(map);
                for (int j = 0; j < cols; j++) {
                    matrix[i][j]=new Stack<Entity>();
                    switch (map.charAt(j)) {
                        case '#':
                            matrix[i][j].push(new Wall(new Coordinates(j,i)));
                            break;
                        case '*':
                            matrix[i][j].push(new Brick(new Coordinates(j,i)));
                            matrix[i][j].push(new Grass(new Coordinates(j,i)));
                            break;
                        case 'x':
                            matrix[i][j].push(new Portal(new Coordinates(j,i)));
                            matrix[i][j].push(new Grass(new Coordinates(j,i)));
                            break;
                        case 'p':
                            matrix[i][j].push(new Bomber(new Coordinates(j,i),BombermanGame.input));
                            BombermanGame.setEntity(matrix[i][j].peek());
                            matrix[i][j].push(new Grass(new Coordinates(j,i)));
                            break;
                        case 'f':
                            matrix[i][j].push(new FlamesItem(new Coordinates(j,i)));
                            matrix[i][j].push(new Grass(new Coordinates(j,i)));
                            break;
                        case 's':
                            matrix[i][j].push(new SpeedItem(new Coordinates(j,i)));
                            matrix[i][j].push(new Grass(new Coordinates(j,i)));
                            break;
                        case '1':
                            matrix[i][j].push(new Balloom(new Coordinates(j,i),true));
                            BombermanGame.setEntity(matrix[i][j].peek());
                            matrix[i][j].push(new Grass(new Coordinates(j,i)));
                        default:
                            matrix[i][j].push(new Grass(new Coordinates(j,i)));
                            break;
                    }
                }
            }
            br.close();
            is.close();
            return matrix;


        } catch (LevelLoaderException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Stack<Entity>[][] getMatrix() {
        return matrix;
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

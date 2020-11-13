package uet.oop.bomberman;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.item.FlamesItem;
import uet.oop.bomberman.entities.item.SpeedItem;
import uet.oop.bomberman.exception.LevelLoaderException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LevelLoader {
    public static LevelLoader instance = null;
    private Entity[][] matrix;
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

    public Entity[][] loadMap(int level) {

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
            Entity[][] matrix = new Entity[rows][cols];
            for (int i = 0; i < rows; i++) {
                String map = br.readLine();
                System.out.println(map);
                for (int j = 0; j < cols; j++) {
                    switch (map.charAt(j)) {
                        case '#':
                            matrix[i][j] = new Wall(new Coordinates(j,i));
                            break;
                        case '*':
                            matrix[i][j] = new Brick(new Coordinates(j,i));
                            break;
                        case 'x':
                            matrix[i][j] = new Portal(new Coordinates(j,i));
                            break;
                        case 'p':
                            matrix[i][j] = new Bomber(new Coordinates(j,i),BombermanGame.input);
                            BombermanGame.setEntity(matrix[i][j]);
                            break;
                        case 'f':
                            matrix[i][j] = new FlamesItem(new Coordinates(j,i));
                            break;
                        case 's':
                            matrix[i][j] = new SpeedItem(new Coordinates(j,i));
                            break;
                        default:
                            matrix[i][j] = new Grass(new Coordinates(j,i));
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

    public Entity[][] getMatrix() {
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

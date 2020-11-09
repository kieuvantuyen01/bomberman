package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.exception.LevelLoaderException;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LevelLoader {
    public static LevelLoader instance = null;
    private Entity[][] matrix;
    public String LEVEL_TEMPLATE = "/levels/Level%d.txt";

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
            if (line.length!=3) {
                throw new LevelLoaderException("Lỗi đọc file: Số hàng, cột");
            }
            int rows = Integer.parseInt(line[1]);
            int cols = Integer.parseInt(line[2]);
            System.out.println(rows + " " + cols);
            Entity[][] matrix = new Entity[rows][cols];
            for (int i = 0; i < rows; i++) {
                String map = br.readLine();
                System.out.println(map);
                for (int j = 0; j < cols; j++) {
                    switch (map.charAt(j)) {
                        case '#':
                            matrix[i][j] = new Wall(j, i, Sprite.wall.getFxImage());
                            break;
                        case '*':
                            matrix[i][j] = new Wall(j, i, Sprite.brick.getFxImage());
                            break;
                        case 'x':
                            matrix[i][j] = new Wall(j, i, Sprite.portal.getFxImage());
                            break;
                        case 'p':
                            matrix[i][j] = new Wall(j, i, Sprite.bomb.getFxImage());
                            break;
                        case 'f':
                            matrix[i][j] = new Wall(j, i, Sprite.powerup_flamepass.getFxImage());
                            break;
                        case 's':
                            matrix[i][j] = new Wall(j, i, Sprite.powerup_speed.getFxImage());
                            break;
                        default:
                            matrix[i][j] = new Grass(j, i, Sprite.grass.getFxImage());
                            break;
                    }
                }
            }
            br.close();
            is.close();
            return matrix;


        } catch (LevelLoaderException e){
            System.err.println(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}

package uet.oop.bomberman.entities;

import com.sun.deploy.util.OrderedHashSet;
import javafx.scene.image.Image;
import uet.oop.bomberman.Bomb;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.Keyboard;

import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends MovableEntity {

    protected BombermanGame game = new BombermanGame();
    protected Keyboard _input = new Keyboard();


    protected static double speed = 1.0;
    protected static int max_bomb = 1;
    protected static boolean flame = false;
    private int bombRemain;
    private final List<Bomb> bombs = new ArrayList<>();
    public static List<Bomb> listBomb = new ArrayList<>();

    public Bomber() {
        this.img = Sprite.player_right.getFxImage();
    }

    public Bomber(Coordinates tile) {
        super(tile);
        this.img = Sprite.player_right.getFxImage();
        this.pixel.setX(this.pixel.getX() + 6);
    }

    public Bomber(Coordinates tile, Image img) {
        super(tile, img);
        this.pixel.setX(this.pixel.getX() + 6);
    }

    public Bomber(Coordinates tile, Keyboard _input) {
        super(tile);
        this.img = Sprite.player_right.getFxImage();
        this.pixel.setX(this.pixel.getX() + 4);
        this._input = _input;
//        this.rectangle = new Rectangle(this.pixel.getX(), this.pixel.getY(), (int) (img.getWidth()), (int) img.getHeight());
    }

    public Bomber(Coordinates tile, Image img, Keyboard _input) {
        super(tile, img);
        this._input = _input;
        //      this.rectangle = new Rectangle(this.pixel.getX(), this.pixel.getY(), (int) (img.getWidth()), (int) img.getHeight());
    }


    @Override
    public void update() {
        checkDead();
        checkItem();
        if (_alive == false) {
            afterDie();
            return;
        }
        animate();

        handleMove();

        chooseSprite(Sprite.player_right,
                Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2,
                Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2,
                Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2,
                Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2);

        showBom();
    }

    @Override
    public void animate() {
        if (_animate > 6000) _animate = 0;
        else _animate++;
    }


    @Override
    protected void handleMove() {
        double xa = 0, ya = 0;
        if ((_input.up && d.getX() == 0 
                && canMoveToDirection(0, -1)) || d.getY() < 0) {
            ya -= speed;
            if (d.getY() >= 0) d.setY(d.getY() - Sprite.SCALED_SIZE);
        }
        if ((_input.down && d.getX() == 0 
                && canMoveToDirection(0, 1)) || d.getY() > 0) {
            ya += speed;
            if (d.getY() <= 0) d.setY(d.getY() + Sprite.SCALED_SIZE);
        }
        if ((_input.left && d.getY() == 0 
                && canMoveToDirection(-1, 0)) || d.getX() < 0) {
            xa -= speed;
            if (d.getX() >= 0) d.setX(d.getX() - Sprite.SCALED_SIZE);
        }
        if ((_input.right && d.getY() == 0 
                && canMoveToDirection(1, 0)) || d.getX() > 0) {
            xa += speed;
            if (d.getX() <= 0) d.setX(d.getX() + Sprite.SCALED_SIZE);
        }

        if (d.getX() != 0 || d.getY() != 0) {
            move(xa * Sprite.PLAYERSPEED, ya * Sprite.PLAYERSPEED);
            d.setX((int) (d.getX() - xa * Sprite.PLAYERSPEED));
            d.setY((int) (d.getY() - ya * Sprite.PLAYERSPEED));
            _moving = true;
        } else {
            if (_input.up) {
                _direction = DIRECTION.UP;
            }
            if (_input.right) {
                _direction = DIRECTION.RIGHT;
            }
            if (_input.down) {
                _direction = DIRECTION.DOWN;
            }
            if (_input.left) {
                _direction = DIRECTION.LEFT;
            }
            _moving = false;
        }

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    public void die() {
        if (!_alive) return;
        this._alive = false;

        this.img = Sprite.player_dead1.getFxImage();

    }

    @Override
    protected void afterDie() {

    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }

    //thêm bom vào stillObjects
    public void showBom() {
        if (_input.space) {
            if (listBomb.size() < max_bomb) {
                Coordinates tmp = new Coordinates(rounding(tile.getX()), rounding(tile.getY()));
                Bomb bomb = new Bomb(tmp
                        , Sprite.bomb.getFxImage(), 200);
                if (listBomb.size() == 0) {
                    listBomb.add(bomb);
                    BombermanGame.stillObjects.add(bomb);
                } else {
                    if (checkListBom(bomb) == false) {
                        listBomb.add(bomb);
                        BombermanGame.stillObjects.add(bomb);
                    }
                }

            }

        }
    }

    public void placeBomb() {
        if (bombRemain > 0) {
            int xB = (int) Math.round((pixel.getX() + 4) / (double) Sprite.SCALED_SIZE);
            int yB = (int) Math.round((pixel.getY() + 4) / (double) Sprite.SCALED_SIZE);
            Coordinates pixel_tileB = new Coordinates(xB, yB);

            for (Bomb bomb : bombs) {
                if (xB * Sprite.SCALED_SIZE == bomb.pixel.getX() && yB * Sprite.SCALED_SIZE
                        == bomb.pixel.getY()) {
                    return;
                }
            }
            bombs.add(new Bomb(pixel_tileB, Sprite.bomb.getFxImage()));
            bombRemain--;
        }
    }

    //kiểm tra những vị trí quả bom hiện tại
    public boolean checkListBom(Bomb b) {
        for (int i = 0; i < listBomb.size(); i++) {
            if (b.getRectangle().intersects(listBomb.get(i).rectangle)) {
                return true;
            }
        }

        return false;
    }

    //set ??m ng??c cho bom v� t?o bom n?
    public static void deadLineAllBom() {
       /* for (int i = 0; i < listBomb.size(); i++) {
            Bomb t = listBomb.get(i);
            if (t.time > 0) {
                listBomb.get(i).deadLineBom();
            } else {
                listBomb.remove(i);
                BombermanGame.stillObjects.remove(t);
                if (flame == false) {
                    listBombBang.add(new BomBang(t.x, t.y,25));
                } else {
                    listBombBang.add(new BomBang(t.x, t.y,25, 0));
                }

                BombermanGame.stillObjects.addAll(listBombBang);
            }

        }*/

        /*for (int i = 0; i < listBombBang.size(); i++) {
            BomBang t = listBombBang.get(i);

            if (t.time > 0) {
                listBombBang.get(i).deadLineBomBang();
            } else {
                listBombBang.remove(i);
                BombermanGame.stillObjects.remove(t);
            }
        }*/
    }

    //ki?m tra ch?t
    public void checkDead() {
        /*Entity c = BombermanGame.getEntity(rectangle);
        if (c instanceof BomBang) {
            //System.out.println("player die");
            kill();
        }

        if (BombermanGame.checkCollisionEnemy(rectangle)) {
            //System.out.println("player die");
            kill();
        }*/
    }

    //check va cham item
    public void checkItem() {
        /*Entity t = BombermanGame.checkCollisionItem(this.rectangle);
        if (t instanceof SpeedItem) {
            speed = 1.5;
            ((SpeedItem) t).afterCollision();
        }

        if (t instanceof FlameItem) {
            flame = true;
            ((FlameItem) t).afterCollision();
        }

        if (t instanceof BombItem) {
            max_bomb = 2;
            ((BombItem) t).afterCollision();
        }*/
    }

    //l�m tr�n s? ?? ??t bom
    public int rounding(double s) {
        if (s - (int) s > 0.5) {
            return (int) (s + 1);
        } else {
            return (int) s;
        }
    }


}

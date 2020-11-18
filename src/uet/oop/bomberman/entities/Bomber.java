package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.Keyboard;

import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Bomber extends DynamicEntity {

    protected BombermanGame game = new BombermanGame();
    protected Keyboard _input = new Keyboard();
    protected int _animate = 0;


    protected static double speed = 1.0;
    protected static int max_bomb = 1;
    protected static boolean flame = false;
    protected static Coordinates d = new Coordinates(0, 0);

    public Bomber() {
        this.img = Sprite.player_right.getFxImage();
    }

    public Bomber(Coordinates tile) {
        super(tile);
        this.img = Sprite.player_right.getFxImage();
        this.pixel.setX(this.pixel.getX()+6);
    }

    public Bomber(Coordinates tile, Image img) {
        super(tile, img);
        this.pixel.setX(this.pixel.getX()+6);
    }

    public Bomber(Coordinates tile, Keyboard _input) {
        super(tile);
        this.img = Sprite.player_right.getFxImage();
        this.pixel.setX(this.pixel.getX()+4);
        this._input = _input;
        this.rectangle = new Rectangle(this.pixel.getX(), this.pixel.getY(), (int) (img.getWidth()), (int) img.getHeight());
    }

    public Bomber(Coordinates tile, Image img, Keyboard _input) {
        super(tile, img);
        this._input = _input;
        this.rectangle = new Rectangle(this.pixel.getX(), this.pixel.getY(), (int) (img.getWidth()), (int) img.getHeight());
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

        chooseSprite();

        showBom();
    }

    //d�ng ?? cho v�o h�m ch?n h�nh ?nh
    public void animate() {
        if (_animate > 6000) _animate = 0;
        else _animate++;
    }


    //ch?n h�nh ?nh khi di chuy?n
    private void chooseSprite() {
        switch (_direction) {
            case 0:
                img = Sprite.player_up.getFxImage();
                if (_moving) {
                    img = Sprite.movingSprite(Sprite.player_up_1.getFxImage(), Sprite.player_up_2.getFxImage(), _animate, 20);
                }
                break;
            case 1:
                img = Sprite.player_right.getFxImage();
                if (_moving) {
                    img = Sprite.movingSprite(Sprite.player_right_1.getFxImage(), Sprite.player_right_2.getFxImage(), _animate, 20);
                }
                break;
            case 2:
                img = Sprite.player_down.getFxImage();
                if (_moving) {
                    img = Sprite.movingSprite(Sprite.player_down_1.getFxImage(), Sprite.player_down_2.getFxImage(), _animate, 20);
                }
                break;
            case 3:
                img = Sprite.player_left.getFxImage();
                if (_moving) {
                    img = Sprite.movingSprite(Sprite.player_left_1.getFxImage(), Sprite.player_left_2.getFxImage(), _animate, 20);
                }
                break;
            default:
                img = Sprite.player_right.getFxImage();
                if (_moving) {
                    img = Sprite.movingSprite(Sprite.player_right_1.getFxImage(), Sprite.player_right_2.getFxImage(), _animate, 20);
                }
                break;
        }
    }

    @Override
    protected void handleMove() {
        double xa = 0, ya = 0;
        if ((_input.up && d.getX()==0 && BombermanGame.getEntityAt(tile.getX(),tile.getY()-1) instanceof Wall ==false) || d.getY() < 0) {
            ya -= speed;
          //  System.out.println(tile.getX()+" "+ tile.getY());
            if (d.getY() >= 0) d.setY(d.getY() - Sprite.SCALED_SIZE);
        }
        if ((_input.down && d.getX()==0 && BombermanGame.getEntityAt(tile.getX(),tile.getY()+1) instanceof Wall ==false)|| d.getY() > 0) {
            ya += speed;
            if (d.getY() <= 0) d.setY(d.getY() + Sprite.SCALED_SIZE);
        }
        if ((_input.left && d.getY()==0 && BombermanGame.getEntityAt(tile.getX()-1,tile.getY()) instanceof Wall ==false) || d.getX() < 0) {
            xa -= speed;
            if (d.getX() >= 0) d.setX(d.getX() - Sprite.SCALED_SIZE);
        }
        if ((_input.right && d.getY()==0 && BombermanGame.getEntityAt(tile.getX()+1,tile.getY()) instanceof Wall ==false) || d.getX() > 0) {
            xa += speed;
            if (d.getX() <= 0) d.setX(d.getX() + Sprite.SCALED_SIZE);
        }

        if (d.getX() != 0 || d.getY() != 0) {
            move(xa * Sprite.PLAYERSPEED, ya * Sprite.PLAYERSPEED);
            d.setX((int) (d.getX() - xa * Sprite.PLAYERSPEED));
            d.setY((int) (d.getY() - ya * Sprite.PLAYERSPEED));
            _moving = true;
        } else {
            _moving = false;
        }

    }

    @Override
    protected void move(double xa, double ya) {
        if (d.getX() > 0) _direction = 1;
        if (d.getX() < 0) _direction = 3;
        if (d.getY() > 0) _direction = 2;
        if (d.getY() < 0) _direction = 0;

        if (canMove(this.rectangle)) { //separate the moves for the player can slide when is colliding
            if (canMove(new Rectangle(pixel.getX(), (int) (pixel.getY() + ya), (int) img.getWidth(), (int) img.getHeight()))) {
                pixel.setY((int) (pixel.getY() + ya));
                this.rectangle = new Rectangle(pixel.getX(), pixel.getY(), (int) img.getWidth(), (int) img.getHeight());
            }

        }

        if (canMove(this.rectangle)) {
            if (canMove(new Rectangle((int) (pixel.getX() + xa), pixel.getY(), (int) img.getWidth(), (int) img.getHeight()))) {
                pixel.setX((int) (pixel.getX() + xa));
                this.rectangle = new Rectangle(pixel.getX(), pixel.getY(), (int) img.getWidth(), (int) img.getHeight());
            }

        }
        tile=pixel.convertPixelToTile();
        System.out.println(tile.getX()+" "+tile.getY());
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
    protected boolean canMove(Rectangle rec) {
        /*Entity a = game.getEntity(rec);
        if (a != null && (a instanceof Wall || a instanceof Brick)) {
            return false;
        }
        else */
        return true;
    }

    //th�m bom v�o stillobjects
    public void showBom() {
        /*if (_input.space) {
            if (listBom.size() < max_bomb) {
                Bomb bom = new Bomb(rounding(x*1.0 / Sprite.SCALED_SIZE), rounding(y*1.0 / Sprite.SCALED_SIZE)
                        , Sprite.bomb.getFxImage(), 200);
                if (listBom.size() == 0 ) {
                    listBom.add(bom);
                    BombermanGame.stillObjects.add(bom);
                } else {
                    if (checkListBom(bom) == false) {
                        listBom.add(bom);
                        BombermanGame.stillObjects.add(bom);
                    }
                }

            }

        }*/
    }

    //ki?m tra nh?ng v? tr� qu? bom hi?n t?i
    //public boolean checkListBom(Bomb b) {
        /*for (int i = 0; i < listBom.size(); i++) {
            if (b.getRectangle().intersects(listBom.get(i).rectangle)) {
                return true;
            }
        }

        return false;*/
    //}

    //set ??m ng??c cho bom v� t?o bom n?
    public static void deadLineAllBom() {
       /* for (int i = 0; i < listBom.size(); i++) {
            Bomb t = listBom.get(i);
            if (t.time > 0) {
                listBom.get(i).deadLineBom();
            } else {
                listBom.remove(i);
                BombermanGame.stillObjects.remove(t);
                if (flame == false) {
                    listBomBang.add(new BomBang(t.x, t.y,25));
                } else {
                    listBomBang.add(new BomBang(t.x, t.y,25, 0));
                }

                BombermanGame.stillObjects.addAll(listBomBang);
            }

        }*/

        /*for (int i = 0; i < listBomBang.size(); i++) {
            BomBang t = listBomBang.get(i);

            if (t.time > 0) {
                listBomBang.get(i).deadLineBomBang();
            } else {
                listBomBang.remove(i);
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

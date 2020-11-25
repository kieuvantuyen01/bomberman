package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.Keyboard;

import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.item.BombsItem;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.entities.item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bomber extends MovableEntity {

    protected BombermanGame game = new BombermanGame();
    protected Keyboard _input = new Keyboard();
    private final List<Bomb> bombs = new ArrayList<>();
    private int bombRemain;
    protected static double speed = 1.0;
    protected static int bomb = 1;
    protected static int distance = 0;
    protected static boolean flame = false;

    public Entity getBombs() {
        return (Entity) bombs;
    }

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
        //game.removeBomb();
        animate();
        if (alive == false) {
            afterDie();
            if (_animate == 120) {
                BombermanGame.removeBomber();
            }
            return;
        }

        putBomb();
        handleMove();
        handleCollision();

        chooseSprite(Sprite.player_right,
                Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2,
                Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2,
                Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2,
                Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2);
        for (int i = 0; i < bombs.size(); i++) {
            Bomb _bomb = bombs.get(i);
            if (!_bomb.isAlive()) {
                bombs.remove(_bomb);
                bomb++;
            }
        }

    }

    @Override
    protected void handleCollision() {
        Entity entity = BombermanGame.getEntityAt(tile.getX(), tile.getY());
        if (entity instanceof Enemy) {
            die();
        }
        if (entity instanceof Item) {
            BombermanGame.removeItem((Item) entity);
            if (entity instanceof BombsItem) {
                bomb++;
            }
            if (entity instanceof SpeedItem) {
                speed++;
            }
        }
    }

    protected void putBomb() {
        if (_input.space && distance < 0 && bomb > 0 &&
                !(BombermanGame.getEntityAt(tile.getX(), tile.getY()) instanceof Bomb)) {
            BombermanGame.setBomb(new Bomb(new Coordinates(tile.getX(), tile.getY())));
            bomb--;
            distance = 10;
            System.out.println(BombermanGame.getBombs().size());

        }
    }

    @Override
    public void animate() {
        if (_animate > 120) _animate = 0;
        else _animate++;
        distance--;
    }


    @Override
    protected void handleMove() {
        double xa = 0, ya = 0;
        if ((_input.up && d.getX() == 0 && canMoveToDirection(0, -1)) || d.getY() < 0) {
            ya -= speed;
            if (d.getY() >= 0) d.setY(d.getY() - Sprite.SCALED_SIZE);
        }
        if ((_input.down && d.getX() == 0 && canMoveToDirection(0, 1)) || d.getY() > 0) {
            ya += speed;
            if (d.getY() <= 0) d.setY(d.getY() + Sprite.SCALED_SIZE);
        }
        if ((_input.left && d.getY() == 0 && canMoveToDirection(-1, 0)) || d.getX() < 0) {
            xa -= speed;
            if (d.getX() >= 0) d.setX(d.getX() - Sprite.SCALED_SIZE);
        }
        if ((_input.right && d.getY() == 0 && canMoveToDirection(1, 0)) || d.getX() > 0) {
            xa += speed;
            if (d.getX() <= 0) d.setX(d.getX() + Sprite.SCALED_SIZE);
        }

        if (d.getX() != 0 || d.getY() != 0) {
            move(xa * Sprite.PLAYERSPEED, ya * Sprite.PLAYERSPEED);
            d.setX((int) (d.getX() - xa * Sprite.PLAYERSPEED));
            d.setY((int) (d.getY() - ya * Sprite.PLAYERSPEED));
            _moving = true;
        } else {
            if (_input.up) _direction = DIRECTION.UP;
            if (_input.right) _direction = DIRECTION.RIGHT;
            if (_input.down) _direction = DIRECTION.DOWN;
            if (_input.left) _direction = DIRECTION.LEFT;
            _moving = false;
        }

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    public void die() {
        if (!alive) return;
        this.alive = false;
        _animate = 0;
    }

    @Override
    protected void afterDie() {
        this.img = Sprite.movingSprite(Sprite.player_dead1.getFxImage(),
                Sprite.player_dead2.getFxImage(),
                Sprite.player_dead3.getFxImage(),
                _animate, 40);
    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }

    public void addBomb() {
        bomb++;
    }

    private void clearBombs() {
        Iterator<Entity> bs = game.getBombs().iterator();

        Bomb b;
        while (bs.hasNext()) {
            b = (Bomb) bs.next();
            if (b.isRemoved()) {
                bs.remove();
                addBomb();
            }
        }
    }
}

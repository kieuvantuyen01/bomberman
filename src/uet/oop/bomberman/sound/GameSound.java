package uet.oop.bomberman.sound;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import uet.oop.bomberman.BombermanGame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.io.InputStream;

public class GameSound {
    protected static AudioPlayer player = AudioPlayer.player;
    private static boolean stop_music = false;

    public static final String MENU = "/sound/menu.wav";
    public static final String PLAYGAME = "/sound/playgame.wav";
    public static final String BOMB = "/sound/newbomb.wav";
    public static final String BOMBER_DIE = "/sound/bomber_die.wav";
    public static final String ENEMY_DIE = "/sound/enemy_die.wav";
    public static final String BONG_BANG = "/sound/bomb_bang.wav";
    public static final String ITEM = "/sound/item.wav";
    public static final String WIN = "/sound/win.wav";
    public static final String LOSE = "/sound/lose.wav";

    public static void playMusic(String musicPath) {
        AudioData data = null;
        try {
            InputStream inputStream = GameSound.class.getResourceAsStream(musicPath);
            AudioStream audioStream = new AudioStream(inputStream);
            if (musicPath.equals(PLAYGAME)) {
                ContinuousAudioDataStream loop;
                data = audioStream.getData();
                loop = new ContinuousAudioDataStream(data);
                player.start(loop);
            } else {
                player.start(audioStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {}
}

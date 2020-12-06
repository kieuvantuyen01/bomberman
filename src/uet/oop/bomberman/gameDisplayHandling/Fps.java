package uet.oop.bomberman.gameDisplayHandling;

public class Fps {
    private static int frame = 0;
    private static int fps = 0;
    private static long start = System.currentTimeMillis() / 1000;
    private static long end = System.currentTimeMillis() / 1000;

    public static void update() {
        frame++;
        end = System.currentTimeMillis() / 1000;
        if (end != start) {
            fps = frame;
            start = end;
            frame = 0;
        }
    }

    public static int get() {
        return fps;
    }
}

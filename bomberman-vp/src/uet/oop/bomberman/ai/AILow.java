package uet.oop.bomberman.ai;

public class AILow extends AI {
    @Override
    public int calculateDirection() {
        int direction = random.nextInt(4);
        return direction;
    }
}

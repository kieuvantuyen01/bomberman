package uet.oop.bomberman;

import java.io.Serializable;
import java.util.Comparator;

public class Score implements Serializable, Comparator<Score> {
    private String name;
    private int points;

    public Score(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public int compare(Score score1, Score score2) {
        return Integer.compare(score1.getPoints(), score2.getPoints());
    }
}
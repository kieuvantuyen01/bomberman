package uet.oop.bomberman.gameManagement;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Score;

import java.io.*;
import java.util.*;
import uet.oop.bomberman.gui.highScoreJframe;

public class TopHighScoreManagement {
    public ArrayList<Score> top_high_scores = new ArrayList<>();
    public ArrayList<String> high_scores_jframe = new ArrayList<>();
    public static int point = 0;
    public static int highest_score = 0;
    public static int lowest_score = 0;

    public static void updatePoint() {
        point = BombermanGame._points;
    }

    public void getScoreChartFromFile() {
        if (top_high_scores.isEmpty() && high_scores_jframe.isEmpty()) {
            try {
                FileReader fileReader = new FileReader("res\\scores\\scoreChart.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line, name, score;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] parts = line.split("\t");
                    if (parts.length == 2) {
                        name = parts[0];
                        score = parts[1];
                        top_high_scores.add(new Score(name, Integer.parseInt(score)));
                    }
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            highest_score = top_high_scores.get(0).getPoints();
            lowest_score = top_high_scores.get(top_high_scores.size()-1).getPoints();
            System.out.println(highest_score + " " + lowest_score);
            int high_score_order = 1;
            for (Score iterator : top_high_scores) {
                high_scores_jframe.add(String.format("%-10s %-35s %-15s",
                        high_score_order + ".", iterator.getName(), iterator.getPoints()));
                high_score_order++;
            }
            for (int i = top_high_scores.size(); i < 10; i++) {
                high_scores_jframe.add(String.format("%-10s %-30s %-15s",
                        high_score_order + ".", "-", 0));
                high_score_order++;
            }
        }
    }

    public void writeToScoreChartFile() {
        try {
            FileWriter fos = new FileWriter("res\\scores\\scoreChart.txt");
            BufferedWriter bw = new BufferedWriter(fos);
            for (Score score : top_high_scores) {
                //System.out.println(top_high_scores.get(score) + "\t" + score);
                bw.write(score.getName() + "\t" + score.getPoints() + "\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addIntoTopHighScores(String name) {
        top_high_scores.add(new Score(name, point));
        top_high_scores.sort((score2, score1) -> {
            Integer int1 = score1.getPoints();
            Integer int2 = score2.getPoints();
            return int1.compareTo(int2);
        });
        if (!high_scores_jframe.isEmpty()) {
            high_scores_jframe.removeAll(high_scores_jframe);
        }
        int high_score_order = 1;
        for (Score score : top_high_scores) {
            high_scores_jframe.add(String.format("%-10s %-30s %-15s",
                    high_score_order + ".", score.getName(), score.getPoints()));
            high_score_order++;
        }
    }

    // Use to limit top high scores = 10.
    public void removeFromTopHighScores() {
        top_high_scores.remove(top_high_scores.size() - 1);
    }

    public void handleScores(String name) {
        getScoreChartFromFile();

        if (top_high_scores.size() > 10) {
            removeFromTopHighScores();
        }
        addIntoTopHighScores(name);
        writeToScoreChartFile();
    }
}
package uet.oop.bomberman.gameManagement;

import uet.oop.bomberman.BombermanGame;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class TopHighScoreManagement {
    public HashMap<Integer, String> top_high_scores = new HashMap<>();
    public ArrayList<Integer> scores = new ArrayList<>();
    public ArrayList<String> high_scores_jframe = new ArrayList<>();
    public static int point = 0;

    public void getScoreChartFromFile() {
        try {
            FileReader fileReader = new FileReader("res\\scores\\scoreChart.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line, name, score;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    name = parts[0];
                    score = parts[1];
                    if(scores.size() <= 10) {
                        scores.add(Integer.parseInt(score));
                        top_high_scores.put(Integer.parseInt(score), name);
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int high_score_order = 1;
        for (int iterator : scores) {
            high_scores_jframe.add(String.format("%-10s %-25s %-25s", String.valueOf(high_score_order) + ".", top_high_scores.get(iterator), String.valueOf(iterator)));
            high_score_order++;
        }
    }

    public void writeToScoreChartFile() {
        try {
            FileWriter fos = new FileWriter("res\\scores\\scoreChart.txt");
            BufferedWriter bw = new BufferedWriter(fos);
            int high_score_order = 1;
            for (int score : scores) {
                bw.write(top_high_scores.get(score) + " " + String.valueOf(score) + "\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addIntoTopHighScores() {
        String name = "T�ng";
        point = BombermanGame.get_points();
        scores.add(point);
        Comparator c = Collections.reverseOrder();
        Collections.sort(scores, c);
        top_high_scores.put(point, name);
        high_scores_jframe.removeAll(high_scores_jframe);
        int high_score_order = 0;
        for (int score : scores) {
            high_scores_jframe.add(String.format("%-6s %-18s %-18s", String.valueOf(high_score_order) + ".", top_high_scores.get(score), String.valueOf(score)));
            high_score_order++;
        }
    }

    //Sử dụng hàm này khi số phần tử của mảng scores đạt mức tối đa là 10.
    public void removeFromTopHighScores() {
        scores.remove(scores.size() - 1);
    }

    public void handleScores() {
        getScoreChartFromFile();
        addIntoTopHighScores();
        if (scores.size() > 10) {
            removeFromTopHighScores();
        }
        writeToScoreChartFile();
    }
}

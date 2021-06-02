package org.example.game;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighScores {

    private List<Integer> scores;

    public HighScores() throws IOException {
        scores = new ArrayList<>();

        BufferedReader bfr = new BufferedReader(new FileReader("scores.txt"));

        while (true) {

            String line = bfr.readLine();
            if (line == null) {
                break;
            }
            int score = Integer.parseInt(line);
            scores.add(score);
        }

        bfr.close();
    }

    public void add(int score) throws IOException {
        scores.add(score);
        scores.sort(Comparator.naturalOrder());

        if (scores.size() > 10) {
            scores.remove(0);
        }
        System.out.println(scores);
        FileOutputStream fos = new FileOutputStream(new File("scores.txt"), false);
        PrintWriter pw = new PrintWriter(fos);

        for (Integer s : scores) {
            pw.println(s);
        }

        pw.close();
    }

    public List<Integer> getScores() {
        return scores;
    }
}

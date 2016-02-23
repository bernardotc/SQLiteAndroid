package com.example.SQLiteTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernardot on 2/23/16.
 */
public class DummyDB implements ScoreDB {
    private ArrayList<Score> scores;

    public DummyDB() {
        scores = new ArrayList<Score>();
    }

    @Override
    public Score highScore() {
        if (scores.size() == 0) {
            return null;
        }
        Score maxScore = scores.get(0);
        for (int x = 1; x < scores.size(); x++) {
            if (scores.get(x).score > maxScore.score) {
                maxScore = scores.get(x);
            }
        }
        return maxScore;
    }

    @Override
    public List<Score> topN(int n) {
        ArrayList<Score> copyScores = new ArrayList<Score>(scores);
        ArrayList<Score> topScores = new ArrayList<Score>();
        for (int x = 0; x < n; x++) {
            Score max = highScore();
            topScores.add(max);
            scores.remove(max);
        }
        scores = copyScores;
        return topScores;
    }

    @Override
    public void addScore(Score score) {
        scores.add(score);
    }
}

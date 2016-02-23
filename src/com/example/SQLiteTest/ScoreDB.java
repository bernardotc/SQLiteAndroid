package com.example.SQLiteTest;

import java.util.List;

public interface ScoreDB {
    Score highScore();
    List<Score> topN(int n);
    void addScore(Score score);
}

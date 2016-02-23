package com.example.SQLiteTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernardot on 2/23/16.
 */
public class DummyDBTest {
    public static void main(String args[]) {
        DummyDB db = new DummyDB();
        db.addScore(new Score(System.currentTimeMillis(), "Bob", 300));
        db.addScore(new Score(System.currentTimeMillis(), "Brian", 600));
        db.addScore(new Score(System.currentTimeMillis(), "Berny", 700));
        db.addScore(new Score(System.currentTimeMillis(), "Ovi", 600));
        db.addScore(new Score(System.currentTimeMillis(), "Beto", 500));
        db.addScore(new Score(System.currentTimeMillis(), "Grace", 420));
        db.addScore(new Score(System.currentTimeMillis(), "Lalo", 410));
        System.out.println(db.highScore().person + " / " + db.highScore().score);
        List<Score> top = db.topN(5);
        for (int x = 0; x < top.size(); x++) {
            System.out.println(top.get(x).person + " / " + top.get(x).score);
        }
        System.out.println(db.highScore().person + " / " + db.highScore().score);
    }
}

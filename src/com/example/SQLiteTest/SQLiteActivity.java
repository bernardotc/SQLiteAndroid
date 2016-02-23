package com.example.SQLiteTest;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Random;

public class SQLiteActivity extends Activity {

    static String TAG = "SQLiteActivity Test: ";
    // LinearLayout layout;
    ScoreHelper scoreHelper;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scoreHelper = new ScoreHelper(this);
        setContentView(R.layout.main);
        addButtons();
        Log.i(TAG, "Created Buttons");
    }

    void addButtons() {
        LinearLayout lin = (LinearLayout) this.findViewById(R.id.myLayout);

        Button createButton = new Button(this);
        createButton.setText("Create Table");
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SQLiteDatabase db = scoreHelper.getWritableDatabase();
                    scoreHelper.onCreate(db);
                    Log.i(TAG, "Created Table");
                    db.close();
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
        lin.addView(createButton);

        Button dropButton = new Button(this);
        dropButton.setText("Drop Table");
        dropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SQLiteDatabase db = scoreHelper.getWritableDatabase();
                    scoreHelper.dropTable(db);
                    Log.i(TAG, "Dropped Table");
                    db.close();
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
        lin.addView(dropButton);

        Button addEntryButton = new Button(this);
        addEntryButton.setText("Add Random Entry");
        addEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SQLiteDatabase db = scoreHelper.getWritableDatabase();
                    SQLiteDB sqlDB = new SQLiteDB(db);
                    sqlDB.addScore(new Score(System.currentTimeMillis(), "Simon", 10 * (1 + random.nextInt(100))));
                    Log.i(TAG, "Populated Table");
                    db.close();
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
        lin.addView(addEntryButton);

        Button HSqueryButton = new Button(this);
        HSqueryButton.setText("High Score Query");
        HSqueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SQLiteDatabase db = scoreHelper.getWritableDatabase();
                    SQLiteDB sqlDB = new SQLiteDB(db);
                    Score highScore = sqlDB.highScore();
                    System.out.println(highScore.person + " / " + highScore.score);
                    db.close();
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
        lin.addView(HSqueryButton);

        Button TOPqueryButton = new Button(this);
        TOPqueryButton.setText("Top 5 Query");
        TOPqueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SQLiteDatabase db = scoreHelper.getWritableDatabase();
                    SQLiteDB sqlDB = new SQLiteDB(db);
                    List<Score> top = sqlDB.topN(5);
                    for (int x = 0; x < top.size(); x++) {
                        System.out.println(top.get(x).person + " / " + top.get(x).score);
                    }
                    db.close();
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
        lin.addView(TOPqueryButton);

    }


    static Random random = new Random();

}

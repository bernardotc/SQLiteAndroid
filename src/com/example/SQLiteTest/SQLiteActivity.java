package com.example.SQLiteTest;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
                    scoreHelper.addEntry(db, "Simon", "" + 10 * (1 + random.nextInt(100)));
                    Log.i(TAG, "Populated Table");
                    db.close();
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
        lin.addView(addEntryButton);

        Button queryButton = new Button(this);
        queryButton.setText("Query");
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SQLiteDatabase db = scoreHelper.getWritableDatabase();
                    try{
                        Cursor cursor = db.rawQuery("Select * from " + ScoreDBContract.ScoreEntry.TABLE_NAME,
                                new String[]{});
                        cursor.moveToFirst();
                        int scoreIndex = cursor.getColumnIndex(ScoreDBContract.ScoreEntry.COLUMN_NAME_SCORE);
                        Log.i(TAG, "Score column index = " + scoreIndex);
                        while (!cursor.isAfterLast()) {
                            // String row = cursor.toString();
                            // note
                            // String score = cursor.getString(scoreIndex);
                            int score = cursor.getInt(scoreIndex);
                            boolean flag = cursor.moveToNext();
                            Log.i(TAG, flag + " : " + score);
                        }
                    }   catch(Exception e) {
                        Log.e(TAG, e.toString());
                    }
                    db.close();
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
        lin.addView(queryButton);

    }


    static Random random = new Random();

}

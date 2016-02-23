package com.example.SQLiteTest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernardot on 2/23/16.
 */
public class SQLiteDB implements ScoreDB {
    SQLiteDatabase db;

    public SQLiteDB (SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public Score highScore() {
        Score result = null;
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + ScoreDBContract.ScoreEntry.TABLE_NAME + " ORDER BY score DESC LIMIT 1",
                    new String[]{});
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String person = cursor.getString(2);
                int score = cursor.getInt(3);
                cursor.move(1);
                result = new Score(System.currentTimeMillis(), person, score);
            }
        }   catch(Exception e) {

        }
        return result;
    }

    @Override
    public List<Score> topN(int n) {
        List<Score> result = new ArrayList<Score>();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + ScoreDBContract.ScoreEntry.TABLE_NAME + " ORDER BY score DESC LIMIT " + n,
                    new String[]{});
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String person = cursor.getString(2);
                int score = cursor.getInt(3);
                cursor.move(1);
                Score aux = new Score(System.currentTimeMillis(), person, score);
                result.add(aux);
            }
        }   catch(Exception e) {

        }
        return result;
    }

    public static int id = 0;

    @Override
    public void addScore(Score score) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ScoreDBContract.ScoreEntry.COLUMN_NAME_ENTRY_ID, id++);
        values.put(ScoreDBContract.ScoreEntry.COLUMN_NAME_PERSON, score.person);
        values.put(ScoreDBContract.ScoreEntry.COLUMN_NAME_SCORE, score.score);


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                ScoreDBContract.ScoreEntry.TABLE_NAME,
                ScoreDBContract.ScoreEntry.COLUMN_NAME_NULLABLE,
                values);
    }
}

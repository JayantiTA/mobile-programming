package com.example.weightsdata;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelperWeight extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "weights_data";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_WEIGHTS = "weights";
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_MAX = "max";
    private static final String KEY_MIN = "min";
    private static final String CREATE_TABLE_WEIGHTS = "CREATE TABLE "
            + TABLE_WEIGHTS + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_DATE + " DATE NOT NULL, "+
            KEY_MAX + " DECIMAL NOT NULL, "+
            KEY_MIN + " DECIMAL NOT NULL" +
            "); ";

    public DatabaseHelperWeight(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_WEIGHTS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_WEIGHTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_WEIGHTS + "'");
        onCreate(db);
    }

    public long addWeightsDetail(String date, double max, double min) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, date);
        values.put(KEY_MAX, max);
        values.put(KEY_MIN, min);
        //insert row in table

        return db.insert(TABLE_WEIGHTS, null, values);
    }

    @SuppressLint("Range")
    public ArrayList<WeightModel> getAllWeights() {
        ArrayList<WeightModel> weightsModelArrayList = new ArrayList<WeightModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_WEIGHTS;
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                WeightModel weightModel = new WeightModel();
                weightModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                weightModel.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
                weightModel.setMax(c.getDouble(c.getColumnIndex(KEY_MAX)));
                weightModel.setMin(c.getDouble(c.getColumnIndex(KEY_MIN)));
                // adding to list
                weightsModelArrayList.add(weightModel);
            } while (c.moveToNext());
        }
        return weightsModelArrayList;
    }

    public int updateWeights(int id, String date, double max, double min) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, date);
        values.put(KEY_MAX, max);
        values.put(KEY_MIN, min);
        // update row in table base on students.is value
        return db.update(TABLE_WEIGHTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id) {

        // delete row in table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WEIGHTS, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}

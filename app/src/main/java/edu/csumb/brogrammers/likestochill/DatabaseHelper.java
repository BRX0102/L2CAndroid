package edu.csumb.brogrammers.likestochill;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bjon on 11/21/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "user_table";
    public static final int COL_1 = 15;
    public static final String COL_2 = "fName";
    public static final String COL_3 = "lName";
    public static final String COL_4 = "location";
    public static final String COL_5 = "email";
    public static final String COL_6 = "gender";
    public static final String COL_7 = "birthday";
    public static final String COL_8 = "about";

    public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FNAME TEXT, LNAME TEXT, LOCATION TEXT, EMAIL TEXT, GENDER TEXT, BIRTHDAY TEXT, ABOUT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

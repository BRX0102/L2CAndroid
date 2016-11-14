package edu.csumb.brogrammers.likestochill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by BRX01 on 11/13/2016.
 */

public class MySQLiteHelper extends SQLiteOpenHelper{
    private static MySQLiteHelper sInstance;
    private SQLiteDatabase database;
    private final Context context;
    private String DATABASE_PATH;


    // Log TAG for debugging purpose
    private static final String TAG = "MYSQL";

    // Database Name - Starving Students
    private static final String DATABASE_NAME = "LikesToChill";

    // Database Version
    private static final int DATABASE_VERSION = 9;

    // Constructor
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        //this.DATABASE_PATH = context.getApplicationInfo().dataDir;
    }

    public MySQLiteHelper(Context context, String db_name, String something, Integer version){
        super(context, db_name, null, version);
        this.context = context;
        this.DATABASE_PATH = context.getApplicationInfo().dataDir;
    }

    public static synchronized MySQLiteHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MySQLiteHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "INSIDE SQLite");

        // SQL statement to create a table called "locations"
        String CREATE_USER_TABLE = "CREATE TABLE user ( " +
                "first_name TEXT, " +
                "last_name TEXT, " +
                "email TEXT, " +
                "dob TEXT, ";

        // execute an SQL statement to create the table
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older user if table exists
        db.execSQL("DROP TABLE IF EXISTS user");
        this.onCreate(db);
    }
}

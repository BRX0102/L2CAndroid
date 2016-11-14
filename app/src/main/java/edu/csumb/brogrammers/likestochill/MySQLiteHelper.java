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

    // User table name
    private static final String TABLE_USER = "user";

    // Column names of User table
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_EMAIL = "email";

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

    public void addUser(User user){
        //For logging
        Log.d(TAG, user.toString());

        //Get reference to writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        //Create ContentValues to add key column/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getUserId());
        values.put(KEY_FIRSTNAME, user.getfName());
        values.put(KEY_LASTNAME, user.getlName());
        values.put(KEY_EMAIL, user.getUserEmail());

        db.insert(TABLE_USER, //table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        db.close();
    }

    public int updateUser(User user){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("id", user.getUserId());
        values.put("firstname", user.getfName()); // get firstname
        values.put("lastname", user.getlName()); // get lastname
        values.put("email", user.getUserEmail()); // get email

        // 3. updating row
        int i = db.update(TABLE_USER, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(user.getUserId()) }); //selection args

        // 4. close
        db.close();

        return i;
    }
}

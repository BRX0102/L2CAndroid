package edu.csumb.brogrammers.likestochill;

import java.text.SimpleDateFormat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");


    // Log TAG for debugging purpose
    private static final String TAG = "MYSQL";

    private static final String DATABASE_NAME = "LikesToChill";
    private static final String TABLE_USER = "user";

    // Column names of User table
    private static final String KEY_USERID = "user_id";
    private static final String KEY_FIRSTNAME = "first_name";
    private static final String KEY_LASTNAME = "last_name";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_DOB = "dob";
    private static final String KEY_ABOUT = "about";

    private static final String[] COLUMNS = {KEY_USERID,KEY_FIRSTNAME,KEY_LASTNAME,KEY_LOCATION,KEY_EMAIL,KEY_GENDER,KEY_DOB,KEY_ABOUT};

    private static final int DATABASE_VERSION = 9;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
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

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +" ( " +
                KEY_USERID + " TEXT, " +
                KEY_FIRSTNAME + " TEXT, " +
                KEY_LASTNAME + " TEXT, " +
                KEY_LOCATION + " INTEGER, " +
                KEY_EMAIL + " TEXT, " +
                KEY_GENDER + " TEXT, " +
                KEY_DOB + " TEXT, "+
                KEY_ABOUT + " TEXT )";

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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
        values.put(KEY_USERID, user.getUserId());
        values.put(KEY_FIRSTNAME, user.getfName());
        values.put(KEY_LASTNAME, user.getlName());
        values.put(KEY_LOCATION, Integer.parseInt(user.getUserLocation()));
        values.put(KEY_EMAIL, user.getUserEmail());
        values.put(KEY_GENDER, user.getUserGender());
        values.put(KEY_DOB, user.getUserDOB());
        values.put(KEY_ABOUT, user.getUserAbout());

        db.insert(TABLE_USER,
                null,
                values);

        db.close();
    }

    public User getUser(String user_id){
        // get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_USER, // a. table
                        COLUMNS, // b. column names
                        "user_id = ?", // c. selections
                        new String[] { user_id }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // build book object
        User user = new User();
        user.setUserId(user_id);
        user.setfName(cursor.getString(1));
        user.setlName(cursor.getString(2));
        user.setUserLocation(cursor.getString(3));
        user.setUserEmail(cursor.getString(4));
        user.setUserGender(cursor.getString(5));
        user.setUserDOB(cursor.getString(6));
        user.setUserAbout(cursor.getString(7));

        Log.d("getUser("+user_id+")", user.toString());

        return user;
    }

    public int updateUser(User user){
        // get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_USERID, user.getUserId());
        values.put(KEY_FIRSTNAME, user.getfName());
        values.put(KEY_LASTNAME, user.getlName());
        values.put(KEY_LOCATION, user.getUserLocation());
        values.put(KEY_EMAIL, user.getUserEmail());
        values.put(KEY_GENDER, user.getUserGender());
        values.put(KEY_DOB, user.getUserDOB());
        values.put(KEY_ABOUT, user.getUserAbout());

        int i = db.update(TABLE_USER,
                values,
                KEY_USERID+" = ?",
                new String[] { user.getUserId() });

        db.close();

        return i;
    }
}

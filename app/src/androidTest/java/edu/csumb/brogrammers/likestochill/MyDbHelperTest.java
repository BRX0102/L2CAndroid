package edu.csumb.brogrammers.likestochill;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by BRX01 on 11/14/2016.
 */

public class MyDbHelperTest extends AndroidTestCase {
    
    private MySQLiteHelper db;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        db = MySQLiteHelper.getInstance(mContext);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    public void testOpeningDb() throws Exception {
        assertNotNull(db);
        SQLiteDatabase sqliteDb = db.getWritableDatabase();
        assertNotNull(sqliteDb);
    }
}

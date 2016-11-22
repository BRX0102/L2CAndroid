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

    @Test
    public void testOpeningDb() throws Exception {
        assertNotNull(db);
        SQLiteDatabase sqliteDb = db.getWritableDatabase();
        assertNotNull(sqliteDb);
    }

    @Test
    public void testWriteToDb() throws Exception{
        assertNotNull(db);
//        db.getWritableDatabase();
        User testUser = new User(1234, "Brian", "Rono", "93955", "brono@csumb.edu", "Male",
                "04/16/1991", "Brian is perfect");
        db.addUser(testUser);
        User tempUser = db.getUser(1234);
        assertEquals("Brian",tempUser.getfName());
    }

    @Test
    public void updateUser() throws Exception{
        User testUser = new User(1234, "Brian", "Rono", "93955", "brono@csumb.edu", "Male",
                "04/16/1991", "Brian is perfect");
        db.addUser(testUser);
        User tempUser = db.getUser(1234);
        assertEquals("Brian",tempUser.getfName());

        testUser = new User(1234, "Arash", "Aria", "93955", "brono@csumb.edu", "Male",
                "04/16/1991", "Brian is perfect");

        db.updateUser(testUser);
        tempUser = db.getUser(1234);
        assertEquals("Arash",tempUser.getfName());
    }
}

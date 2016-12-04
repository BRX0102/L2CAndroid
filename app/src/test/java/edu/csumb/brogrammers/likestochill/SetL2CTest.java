package edu.csumb.brogrammers.likestochill;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Brian on 12/3/2016.
 */

public class SetL2CTest {
    private UserL2C userTest;
    @Before
    public void create(){
//        String fName, String lName, String userId, String userAbout, String userDOB, String userEmail, String userGender, String userLocation
        userTest = new UserL2C("Sean", "OFallon", "1", "About Sean", "2016-10-27", "sofallon@csumb.edu", "M", "93955");
    }
    @Test
    public void setUserId() throws Exception {
        Boolean test = false;
        try{
            userTest.setUserId("100");
            if(userTest.getUserId().equals("100"))
            {
                test=true;
            }
        }catch(Exception e){
            test=false;
        }

        assertTrue(test);
    }

    @Test
    public void setFirstName() throws Exception {
        Boolean test = false;
        try{
            userTest.setfName("Chet");
            if(userTest.getfName().equals("Chet"))
            {
                test=true;
            }
        }catch(Exception e){
            test=false;
        }

        assertTrue(test);
    }

    @Test
    public void setLastName() throws Exception {
        Boolean test = false;
        try{
            userTest.setlName("Aria");
            if(userTest.getlName().equals("Aria"))
            {
                test=true;
            }
        }catch(Exception e){
            test=false;
        }

        assertTrue(test);
    }

    @Test
    public void setLocation() throws Exception {
        Boolean test = false;
        try{
            userTest.setUserLocation("93933");
            if(userTest.getUserLocation().equals("93933"))
            {
                test=true;
            }
        }catch(Exception e){
            test=false;
        }

        assertTrue(test);
    }

    @Test
    public void setEmail() throws Exception {
        Boolean test = false;
        try{
            userTest.setUserEmail("aaria@csumb.edu");
            if(userTest.getUserEmail().equals("aaria@csumb.edu"))
            {
                test=true;
            }
        }catch(Exception e){
            test=false;
        }

        assertTrue(test);
    }

    @Test
    public void setGender() throws Exception {
        Boolean test = false;
        try{
            userTest.setUserGender("F");
            if(userTest.getUserGender().equals("F"))
            {
                test=true;
            }
        }catch(Exception e){
            test=false;
        }

        assertTrue(test);
    }

    @Test
    public void setDOB() throws Exception {
        Boolean test = false;
        try{
            userTest.setUserDOB("2016-12-03");
            if(userTest.getUserDOB().equals("2016-12-03"))
            {
                test=true;
            }
        }catch(Exception e){
            test=false;
        }

        assertTrue(test);
    }

    @Test
    public void setAbout() throws Exception {
        Boolean test = false;
        try{
            userTest.setUserAbout("Beautiful");
            if(userTest.getUserAbout().equals("Beautiful"))
            {
                test=true;
            }
        }catch(Exception e){
            test=false;
        }

        assertTrue(test);
    }
}

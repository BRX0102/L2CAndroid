package edu.csumb.brogrammers.likestochill;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Brian on 12/3/2016.
 */

public class setUserL2CTest {
    private UserL2C userTest;
    @Before
    public void create(){
//        String fName, String lName, String userId, String userAbout, String userDOB, String userEmail, String userGender, String userLocation
        userTest = new UserL2C("Sean", "OFallon", "1", "About Sean", "2016-10-27", "sofallon@csumb.edu", "M", "93955");
    }
    @Test
    public void getUserId() throws Exception {
        Boolean idIs1 = false;
        try{
            if(userTest.getUserId().equals("1"))
            {
                idIs1=true;
            }
        }catch(Exception e){
            idIs1=false;
        }

        assertTrue(idIs1);
    }

    @Test
    public void getFirstName() throws Exception{
        Boolean name = false;
        try{
            if(userTest.getfName().equals("Sean"))
            {
                name=true;
            }
        }catch(Exception e){
            name=false;
        }

        assertTrue(name);
    }

    @Test
    public void getLastName() throws Exception{
        Boolean name = false;
        try{
            if(userTest.getlName().equals("OFallon"))
            {
                name=true;
            }
        }catch(Exception e){
            name=false;
        }

        assertTrue(name);
    }

    @Test
    public void getLocation() throws Exception{
        Boolean location = false;
        try{
            if(userTest.getUserLocation().equals("93955"))
            {
                location=true;
            }
        }catch(Exception e){
            location=false;
        }
        assertTrue(location);
    }

    @Test
    public void getEmail() throws Exception{
        Boolean email = false;
        try{
            if(userTest.getUserEmail().equals("sofallon@csumb.edu"))
            {
                email=true;
            }
        }catch(Exception e){
            email=false;
        }
        assertTrue(email);
    }

    @Test
    public void getGender() throws Exception{
        Boolean gender = false;
        try{
            if(userTest.getUserGender().equals("M"))
            {
                gender=true;
            }
        }catch(Exception e){
            gender=false;
        }
        assertTrue(gender);
    }

    @Test
    public void getDOB() throws Exception{
        Boolean dob = false;
        try{
            if(userTest.getUserDOB().equals("2016-10-27"))
            {
                dob=true;
            }
        }catch(Exception e){
            dob=false;
        }
        assertTrue(dob);
    }

    @Test
    public void getAbout() throws Exception{
        Boolean about = false;
        try{
            if(userTest.getUserAbout().equals("About Sean"))
            {
                about=true;
            }
        }catch(Exception e){
            about=false;
        }
        assertTrue(about);
    }
}

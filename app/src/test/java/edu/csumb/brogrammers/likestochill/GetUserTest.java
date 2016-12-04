package edu.csumb.brogrammers.likestochill;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Brian on 12/4/2016.
 */
@RunWith(JUnit4.class)
public class GetUserTest {
    private User userTest;
    @Before
    public void create(){
        userTest = new User("1", "Sean", "OFallon", "93955", "sofallon@csumb.edu", "M", "2016-10-27", "About Sean");
    }

    @Test
    public void blankUser(){
        User user = new User();
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

    @Test
    public void toStringTest() throws Exception{
        assertTrue(userTest.toString().equalsIgnoreCase(userTest.toString()));
    }
}

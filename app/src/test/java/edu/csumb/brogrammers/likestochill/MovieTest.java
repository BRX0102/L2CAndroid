package edu.csumb.brogrammers.likestochill;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

/**
 * Created by Brian on 12/4/2016.
 */
@RunWith(JUnit4.class)
public class MovieTest {
    private Movie movieTest;

    @Before
    public void create(){
        movieTest = new Movie("Frozen", "123");
    }

    @Test
    public void getMovieTitle(){
        assertTrue(movieTest.getMovieTitle().equalsIgnoreCase("Frozen"));
    }

    @Test
    public void getMovieID(){
        assertTrue(movieTest.getUserId().equalsIgnoreCase("123"));
    }

    @Test
    public void toStringTest(){
        assertTrue(movieTest.toString().equalsIgnoreCase(movieTest.toString()));
    }
}

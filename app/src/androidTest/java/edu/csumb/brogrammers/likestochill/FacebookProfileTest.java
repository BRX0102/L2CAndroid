package edu.csumb.brogrammers.likestochill;

import com.facebook.Profile;
import android.test.AndroidTestCase;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Brian on 11/22/2016.
 */

public class FacebookProfileTest extends AndroidTestCase {
    @Test
    public void isLoggedIn(){
        Profile profile = Profile.getCurrentProfile();
        assertNotNull(profile);
    }

    @Test
    public void getProfileName(){
        Profile profile = Profile.getCurrentProfile();

            Log.d("PROFILETEST",profile.getFirstName().toString());

    }

}

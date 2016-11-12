package edu.csumb.brogrammers.likestochill;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Peter on 11/5/2016.
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest {

//    @Rule
//    public ActivityTestRule<Login> mLoginActivityTestRule = new ActivityTestRule<Login>(Login.class);
//
//    @Test
//    public void currentUserIsLoggedIn() {
//        onView(withId(R.id.textView2)).check(matches(withText("You are logged in")));
//    }
//
//    @Test
//    public void logoutButtonClick() {
//        onView(withId(R.id.button2))
//                .perform(click());
//        onView(withId(R.id.text_details))
//                .check(matches(withText("You are logged out")));
//    }
//
//
//    /*@Test
//    public void clickFaceBookLoginButton_opensLogin() throws Exception {
//        assertNotNull(MainActivity.thisUser);
//    }*/
//        /*onView(withId(R.id.text_details))
//                .check(matches(withText("Welcome Ritty")));
//    }*/
//
//
//    public static ViewAction clickXY(final int x, final int y){
//        return new GeneralClickAction(
//                Tap.SINGLE,
//                new CoordinatesProvider() {
//                    @Override
//                    public float[] calculateCoordinates(View view) {
//
//                        final int[] screenPos = new int[2];
//                        view.getLocationOnScreen(screenPos);
//
//                        final float screenX = screenPos[0] + x;
//                        final float screenY = screenPos[1] + y;
//                        float[] coordinates = {screenX, screenY};
//
//                        return coordinates;
//                    }
//                },
//                Press.FINGER);
//    }

}

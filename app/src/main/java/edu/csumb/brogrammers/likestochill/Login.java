package edu.csumb.brogrammers.likestochill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BRX01 on 11/11/2016.
 */

public class Login extends AppCompatActivity {

    String name;
    TextView mTextDetails;
    Context context;
    LoginButton loginButton;
    AccessToken accessToken;
    private CallbackManager mCallbackManager;
    Button button3;

        /*@Override
        public boolean onTouchEvent(MotionEvent event) {
            // MotionEvent object holds X-Y values
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                String text = "You click at x = " + event.getX() + " and y = " + event.getY();
                Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            }

            return super.onTouchEvent(event);
        }*/




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login);

        mCallbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                loginButton.setReadPermissions("public_profile","user_friends","email","user_hometown","user_birthday","user_about_me","user_location");
                loginButton.registerCallback(mCallbackManager, mCallback);
            }
        });



    }


    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();

            //Log.d("hiya", profile.getName());

            displayWelcomeMessage(profile);

            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            // Application code
                            Log.d("response", String.valueOf(object));

                            try {

                                JSONObject getLocation = object.getJSONObject("location");
                                Object locationName = getLocation.get("name");
                                //Log.d("check1n", level.toString());

                                ///testing JSON call
                                String fName = object.getString("name");
                                Log.d("LOGIN", fName);

                                User theUser = (User) getApplicationContext();
                                theUser.setUserId(object.getInt("id"));

                                Log.d("LOGINUSER", theUser.getfName());
                                theUser.setfName(object.getString("first_name"));
                                theUser.setlName(object.getString("last_name"));
                                theUser.setUserLocation(locationName.toString());
                                theUser.setUserEmail(object.getString("email"));
                                theUser.setUserGender(object.getString("gender"));
                                theUser.setUserDOB(object.getString("birthday"));
                                theUser.setUserAbout(object.getString("about"));


                            } catch (JSONException e) {

                                // Do something to recover ... or kill the app.
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id, first_name, last_name, email, gender, hometown, birthday, about, location");
            request.setParameters(parameters);
            request.executeAsync();

            //Log.d("TAG", bundle2string(parameters));

            ////////COMMENTED CHANGE INTENT TO TEST LOGIN///////////////////////
            Intent toUpdate = new Intent(Login.this, UpdateProfile.class);
            startActivity(toUpdate);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    private void displayWelcomeMessage(Profile profile){
        if(profile != null){
            mTextDetails = (TextView)findViewById(R.id.text_details);
            mTextDetails.setText("Welcome " + profile.getName());
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayWelcomeMessage(profile);


        if(profile != null){
            Intent toUpdate = new Intent(Login.this, UpdateProfile.class);
            startActivity(toUpdate);
        }


            /*Intent toUpdate = new Intent(Login.this, TempLogout.class);
            startActivity(toUpdate);*/
//        Intent to main if resuming

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }



}
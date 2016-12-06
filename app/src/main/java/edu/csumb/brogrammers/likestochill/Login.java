package edu.csumb.brogrammers.likestochill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by BRX01 on 11/11/2016.
 */

public class Login extends AppCompatActivity {

    TextView mTextDetails;
    LoginButton loginButton;
    AccessToken accessToken;
    private CallbackManager mCallbackManager;
    private MySQLiteHelper db;

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

    public class getIfExists extends AsyncTask<Void, Void, Boolean> {

        private Exception exception;
        String userId;
        private getIfExists(String userId) {
            this.userId = userId;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            try {

                okhttp3.OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new Request.Builder()
                        .url("http://lowcost-env.8jm8a7kdcg.us-west-2.elasticbeanstalk.com/webapi/users/"+userId)
                        .build();

                okhttp3.Response response = client.newCall(request).execute();
                String result = response.body().string();

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<UserL2C>>() {}.getType();
                Collection<UserL2C> userListJson = gson.fromJson(result,collectionType);
                if (userListJson.size()>0){
                    return true;
                } else {
                    return false;
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return false;

        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);

            if (s) {
                Toast.makeText(getApplicationContext(), "Welcome back", Toast.LENGTH_SHORT).show();

                Intent toLikes = new Intent(Login.this, MainActivity.class);
                startActivity(toLikes);

            } else {

                Intent toLikes = new Intent(Login.this, NewProfile.class);
                startActivity(toLikes);

            }
        }
    }

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            accessToken = loginResult.getAccessToken();
            final Profile profile = Profile.getCurrentProfile();

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

                                db = MySQLiteHelper.getInstance(getApplicationContext());
                                User user = new User(
                                        object.getString("id"),
                                        object.getString("first_name"),
                                        object.getString("last_name"),
                                        "99999",
                                        object.getString("email"),
                                        object.getString("gender"),
                                        "",
                                        "");

                                db.addUser(user);

                                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("pref", getApplicationContext().MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("user_id", object.getString("id"));
                                editor.apply();

                                new getIfExists(object.getString("id")).execute();


                            } catch (JSONException e) {
                                Log.d("LOGINERROR", e.toString());
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id, first_name, last_name, email, gender, hometown, birthday, about, location");
            request.setParameters(parameters);
            request.executeAsync();
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

//        Intent to main if resuming
        if(profile != null){
            Intent toMain = new Intent(Login.this, MainActivity.class);
            startActivity(toMain);
            this.finish();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
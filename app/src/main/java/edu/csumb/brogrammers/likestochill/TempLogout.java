package edu.csumb.brogrammers.likestochill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

/**
 * Created by BRX01 on 11/11/2016.
 */


public class TempLogout extends AppCompatActivity {

    Button temp_button;
    TextView temp_tv;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.temp_logout);

        Profile profile = Profile.getCurrentProfile();

        temp_button = (Button)findViewById(R.id.button2);
        temp_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                gotoLogin(v);
            }
        });
    }

    public void gotoLogin(View view){
        FacebookSdk.sdkInitialize(getApplicationContext());
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private void displayWelcomeMessage(Profile profile){
        if(profile != null){
            temp_tv = (TextView)findViewById(R.id.textView2);
            temp_tv.setText("Welcome " + profile.getName());

        }
    }


}
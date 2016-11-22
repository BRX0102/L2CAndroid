package edu.csumb.brogrammers.likestochill;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Brian on 11/21/2016.
 */

public class Settings extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

//        Grab the buttons. Set onClickListeners
        ImageButton goToPreferences = (ImageButton)findViewById(R.id.goToPreferencesButton);
        goToPreferences.setOnClickListener(this);

        ImageButton goToHome = (ImageButton)findViewById(R.id.goToHomeButton);
        goToHome.setOnClickListener(this);

        ImageButton goToUpdateProfile = (ImageButton)findViewById(R.id.goToUpdateProfile);
        goToUpdateProfile.setOnClickListener(this);

//        Set the Image, Text

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.goToPreferencesButton){
            Intent i = new Intent(this,SearchPreferences.class);
            startActivity(i);
        }else if(v.getId() == R.id.goToHomeButton){
            this.finish();
        }else if(v.getId() == R.id.goToUpdateProfile){
            Intent i = new Intent(this,UpdateProfile.class);
            startActivity(i);
        }
    }
}

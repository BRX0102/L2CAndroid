package edu.csumb.brogrammers.likestochill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by BRX01 on 11/11/2016.
 */

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        View updateButton = findViewById(R.id.updateNextButton);
        updateButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.updateNextButton){
//            Send the data to the database



//            Move to the main activity
            Intent toMain = new Intent(this, MainActivity.class);
            startActivity(toMain);
        }
    }
}
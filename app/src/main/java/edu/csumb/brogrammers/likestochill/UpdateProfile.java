package edu.csumb.brogrammers.likestochill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by BRX01 on 11/11/2016.
 */

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {
    EditText firstNameUpdate;
    EditText lastNameUpdate;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        View updateButton = findViewById(R.id.updateNextButton);
        updateButton.setOnClickListener(this);

        User theUser = (User) getApplicationContext();
        firstNameUpdate = (EditText)findViewById(R.id.editTextFirstNameUpdate);
        firstNameUpdate.setText(theUser.getfName());
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
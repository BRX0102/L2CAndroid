package edu.csumb.brogrammers.likestochill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.facebook.FacebookSdk;
import com.facebook.Profile;

/**
 * Created by BRX01 on 11/11/2016.
 */

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {
    EditText firstNameUpdate, lastNameUpdate, locationUpdate, emailUpdate, dobUpdate, aboutUpdate;
    RadioGroup rg;
    String user_id;
    private MySQLiteHelper db;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        db = MySQLiteHelper.getInstance(getApplicationContext());

        FacebookSdk.sdkInitialize(getApplicationContext());
        Profile profile = Profile.getCurrentProfile();
        if(profile.getId()==null){
            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("pref", getApplicationContext().MODE_PRIVATE);
            user_id = sharedPref.getString("user_id", "DEFAULT");
        }else{
            user_id = profile.getId();
        }
        View updateButton = findViewById(R.id.updateNextButton);
        updateButton.setOnClickListener(this);

//        Show the user information
        User user = db.getUser(profile.getId());
        firstNameUpdate = (EditText)findViewById(R.id.editTextFirstNameUpdate);
        firstNameUpdate.setText(user.getfName());

        lastNameUpdate = (EditText)findViewById(R.id.editTextLastNameUpdate);
        lastNameUpdate.setText(user.getlName());

        locationUpdate = (EditText)findViewById(R.id.editTextlocationUpdate);
        locationUpdate.setText(user.getUserLocation());

        emailUpdate = (EditText)findViewById(R.id.editTextEmailUpdate);
        emailUpdate.setText(user.getUserEmail());

        rg = (RadioGroup)findViewById(R.id.radioGenderUpdate);
        if(user.getUserGender().equalsIgnoreCase("male")){
            rg.check(R.id.radioMale);
        }else{
            rg.check(R.id.radioFemale);
        }

        dobUpdate = (EditText)findViewById(R.id.editTextDOBUpdate);
        dobUpdate.setText(user.getUserDOB());

        aboutUpdate = (EditText)findViewById(R.id.editTextAboutUpdate);
        aboutUpdate.setText(user.getUserAbout());

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.updateNextButton){
//            Send the data to the local database
            db = MySQLiteHelper.getInstance(getApplicationContext());

            FacebookSdk.sdkInitialize(getApplicationContext());
            Profile profile = Profile.getCurrentProfile();

            firstNameUpdate = (EditText)findViewById(R.id.editTextFirstNameUpdate);
            lastNameUpdate = (EditText)findViewById(R.id.editTextLastNameUpdate);
            locationUpdate = (EditText)findViewById(R.id.editTextlocationUpdate);
            emailUpdate = (EditText)findViewById(R.id.editTextEmailUpdate);
            rg = (RadioGroup)findViewById(R.id.radioGenderUpdate);
            String gender = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
            dobUpdate = (EditText)findViewById(R.id.editTextDOBUpdate);
            aboutUpdate = (EditText)findViewById(R.id.editTextAboutUpdate);

            User updateUser = new User(profile.getId(), firstNameUpdate.getText().toString(), lastNameUpdate.getText().toString(),
                    locationUpdate.getText().toString(), emailUpdate.getText().toString(), gender,
                    dobUpdate.getText().toString(), aboutUpdate.getText().toString());


            db.updateUser(updateUser);

//            Move to the main activity
            Intent toSettings = new Intent(this, Settings.class);
            startActivity(toSettings);
            this.finish();
        }
    }
}
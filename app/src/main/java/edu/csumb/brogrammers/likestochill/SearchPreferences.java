package edu.csumb.brogrammers.likestochill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.facebook.Profile;

/**
 * Created by Brian on 11/17/2016.
 */

public class SearchPreferences extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    EditText minPref, maxPref;
    Spinner genderPref;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_preferences);

        Button submitPreferences = (Button)findViewById(R.id.searchPreferencesSubmit);
        submitPreferences.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.searchPreferencesSubmit){

            Profile profile = Profile.getCurrentProfile();
            minPref = (EditText)findViewById(R.id.ageMinPreferences);
            maxPref = (EditText)findViewById(R.id.ageMaxPreferences);
            genderPref = (Spinner)findViewById(R.id.preferenceGender);

            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

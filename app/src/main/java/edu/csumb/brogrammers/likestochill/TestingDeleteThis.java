package edu.csumb.brogrammers.likestochill;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;

/**
 * Created by Bjon on 11/26/2016.
 */

public class TestingDeleteThis extends AppCompatActivity {

    TextView mTextDetails;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.testing_delete_this);

        Profile profile = Profile.getCurrentProfile();

        mTextDetails=(TextView)findViewById(R.id.textView3);

        mTextDetails.setText(profile.getId());

    }
}

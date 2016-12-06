package edu.csumb.brogrammers.likestochill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by Brian on 11/21/2016.
 */

public class Settings extends AppCompatActivity implements View.OnClickListener {
    TextView firstNameSettings, lastNameSettings, locationSettings, emailSettings, dobSettings, aboutSettings;
    RadioGroup rg;
    private MySQLiteHelper db;
    String user_id;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        db = MySQLiteHelper.getInstance(getApplicationContext());

                SharedPreferences sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE);
                user_id = sharedPref.getString("user_id", "DEFAULT");

        User user = db.getUser(user_id);

        // Grab the buttons. Set onClickListeners
        ImageButton goToPreferences = (ImageButton)findViewById(R.id.goToPreferencesButton);
        goToPreferences.setOnClickListener(this);

        ImageButton goToHome = (ImageButton)findViewById(R.id.goToHomeButton);
        goToHome.setOnClickListener(this);

        ImageButton goToUpdateProfile = (ImageButton)findViewById(R.id.goToUpdateProfile);
        goToUpdateProfile.setOnClickListener(this);

        // Set Profile picture
        new DownloadImageTask((ImageView) findViewById(R.id.profileImage))
                .execute("https://graph.facebook.com/"+user_id+"/picture?type=large");

        firstNameSettings = (TextView)findViewById(R.id.firstNameSettings);
        firstNameSettings.setText(user.getfName());

        lastNameSettings = (TextView)findViewById(R.id.lastNameSettings);
        lastNameSettings.setText(user.getlName().substring(0,1).toUpperCase());

        // Set age
        dobSettings = (TextView)findViewById(R.id.ageSettings);
        dobSettings.setText(user.getUserDOB());

        // Set gender
        if(user.getUserGender().equalsIgnoreCase("male")){
            ((TextView)findViewById(R.id.genderSettings)).setText("Male");
        }else{
            ((TextView)findViewById(R.id.genderSettings)).setText("Female");
        }

        // Set about
        aboutSettings = (TextView)findViewById(R.id.aboutSettings);
        aboutSettings.setText(user.getUserAbout());
    }

    @Override
    public void onBackPressed() {
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
        finish();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.goToPreferencesButton){
            Intent i = new Intent(this,SearchPreferences.class);
            startActivity(i);
        }else if(v.getId() == R.id.goToHomeButton){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            this.finish();
        }else if(v.getId() == R.id.goToUpdateProfile){
            Intent i = new Intent(this,UpdateProfile.class);
            startActivity(i);
        }
    }
}

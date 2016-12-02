package edu.csumb.brogrammers.likestochill;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

//        if(getIntent().hasExtra("user_id")){
//            user_id = getIntent().getExtras().getString("user_id");
//        }else{
//            user_id="10154628186114814";
//            FacebookSdk.sdkInitialize(getApplicationContext());
//            Profile profile = Profile.getCurrentProfile();
//            if(profile.getId()==null){
                SharedPreferences sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE);
                user_id = sharedPref.getString("user_id", "DEFAULT");
//            }else{
//                user_id = profile.getId();
//            }
//        }

        User user = db.getUser(user_id);




//        Grab the buttons. Set onClickListeners
        ImageButton goToPreferences = (ImageButton)findViewById(R.id.goToPreferencesButton);
        goToPreferences.setOnClickListener(this);

        ImageButton goToHome = (ImageButton)findViewById(R.id.goToHomeButton);
        goToHome.setOnClickListener(this);

        ImageButton goToUpdateProfile = (ImageButton)findViewById(R.id.goToUpdateProfile);
        goToUpdateProfile.setOnClickListener(this);

//        Set the Image, Text

//        Profile picture
        new DownloadImageTask((ImageView) findViewById(R.id.profileImage))
                .execute("https://graph.facebook.com/"+user_id+"/picture?type=large");
//        First and Last name
        firstNameSettings = (TextView)findViewById(R.id.firstNameSettings);
        firstNameSettings.setText(user.getfName());

        lastNameSettings = (TextView)findViewById(R.id.lastNameSettings);
        lastNameSettings.setText(user.getlName().substring(0,1).toUpperCase());

//        Set age
        dobSettings = (TextView)findViewById(R.id.ageSettings);
        dobSettings.setText(user.getUserDOB());

//        Set gender
        if(user.getUserGender().equalsIgnoreCase("male")){
            ((TextView)findViewById(R.id.genderSettings)).setText("Male");
        }else{
            ((TextView)findViewById(R.id.genderSettings)).setText("Female");
        }

//        Set about
        aboutSettings = (TextView)findViewById(R.id.aboutSettings);
        aboutSettings.setText(user.getUserAbout());


    }

//    public static Bitmap getFacebookProfilePicture(String userID) throws IOException{
//        Log.d("PROFILEPICID", userID);
//        URL imageURL = new URL("https://graph.facebook.com/" + "10154628186114814" + "/picture?type=large");
//        Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
//
//        return bitmap;
//    }


//    public static Bitmap getBitmapFromURL(String userID) {
//        try {
//            URL url = new URL("http://graph.facebook.com/" + "10154628186114814" + "/picture?type=large");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//            return myBitmap;
//        } catch (IOException e) {
//            // Log exception
//            return null;
//        }
//    }

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

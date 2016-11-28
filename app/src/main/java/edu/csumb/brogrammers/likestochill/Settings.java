package edu.csumb.brogrammers.likestochill;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Brian on 11/21/2016.
 */

public class Settings extends AppCompatActivity implements View.OnClickListener {

    private MySQLiteHelper db;
    String user_id;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        db = MySQLiteHelper.getInstance(getApplicationContext());

        user_id = getIntent().getExtras().getString("user_id");

        User user = db.getUser(user_id);


//        FacebookSdk.sdkInitialize(getApplicationContext());
//        Profile profile = Profile.getCurrentProfile();
//
//
//        if(profile.getId()==null){
//            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("pref", getApplicationContext().MODE_PRIVATE);
//            user_id = sharedPref.getString("user_id", "DEFAULT");
//        }else{
//            user_id = profile.getId();
//        }

//        Grab the buttons. Set onClickListeners
        ImageButton goToPreferences = (ImageButton)findViewById(R.id.goToPreferencesButton);
        goToPreferences.setOnClickListener(this);

        ImageButton goToHome = (ImageButton)findViewById(R.id.goToHomeButton);
        goToHome.setOnClickListener(this);

        ImageButton goToUpdateProfile = (ImageButton)findViewById(R.id.goToUpdateProfile);
        goToUpdateProfile.setOnClickListener(this);

//        Set the Image, Text
//        try{
//            ImageView profileImage = (ImageView)findViewById(R.id.profileImage);
//            profileImage.setImageBitmap(getFacebookProfilePicture(user_id));
//        }catch(IOException e){
//            throw new RuntimeException(e);
//        }


    }

//    public static Bitmap getFacebookProfilePicture(String userID) throws IOException{
//        Log.d("PROFILEPICID", userID);
//        URL imageURL = new URL("https://graph.facebook.com/" + "10154628186114814" + "/picture?type=large");
//        Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
//
//        return bitmap;
//    }

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

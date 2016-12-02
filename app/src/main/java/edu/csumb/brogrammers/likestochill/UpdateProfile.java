package edu.csumb.brogrammers.likestochill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by BRX01 on 11/11/2016.
 */

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {
    EditText firstNameUpdate, lastNameUpdate, locationUpdate, emailUpdate, dobUpdate, aboutUpdate;
    RadioGroup rg;
    String user_id;
    private MySQLiteHelper db;


    okhttp3.OkHttpClient client = new OkHttpClient();
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

//        new JSONFeedArrayTask().execute();

        db = MySQLiteHelper.getInstance(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            user_id = getIntent().getExtras().getString("user_id");
        }else{
            SharedPreferences sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE);
            user_id = sharedPref.getString("user_id", "DEFAULT");
        }

        View updateButton = findViewById(R.id.updateNextButton);
        updateButton.setOnClickListener(this);

//        Show the user information
        User user = db.getUser(user_id);

        SharedPreferences sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user_id", user_id);
        editor.apply();

        firstNameUpdate = (EditText)findViewById(R.id.editTextFirstNameUpdate);
        firstNameUpdate.setText(user.getfName());

        lastNameUpdate = (EditText)findViewById(R.id.editTextLastNameUpdate);
        lastNameUpdate.setText(user.getlName());

        locationUpdate = (EditText)findViewById(R.id.editTextlocationUpdate);
        if(!user.getUserLocation().equals("99999")){
            locationUpdate.setText(user.getUserLocation());
        }

        emailUpdate = (EditText)findViewById(R.id.editTextEmailUpdate);
        emailUpdate.setText(user.getUserEmail());

        rg = (RadioGroup)findViewById(R.id.radioGenderUpdate);
        if(user.getUserGender().equalsIgnoreCase("male")){
            rg.check(R.id.radioMale);
        }else{
            rg.check(R.id.radioFemale);
        }

        dobUpdate = (EditText)findViewById(R.id.editTextDOBUpdate);
        if(!user.getUserDOB().equals("")){
            dobUpdate.setText(user.getUserDOB());
        }

        aboutUpdate = (EditText)findViewById(R.id.editTextAboutUpdate);
        if(!user.getUserAbout().equals("")){
            aboutUpdate.setText(user.getUserAbout());
        }

        new getIfExists(this).execute();

    }

    public class getIfExists extends AsyncTask<Void, Void, Boolean> {

        private Exception exception;
        Context context;

        private getIfExists(Context context) {
            this.context = context.getApplicationContext();
        }


        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                okhttp3.OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new Request.Builder()
                        .url("http://lowcost-env.8jm8a7kdcg.us-west-2.elasticbeanstalk.com/webapi/users/"+user_id)
                        .build();

                okhttp3.Response response = client.newCall(request).execute();
                String result = response.body().string();

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<UserL2C>>() {}.getType();
                Collection<UserL2C> userListJson = gson.fromJson(result,collectionType);
                if (userListJson.size()>0){
                    return true;
                } else {
                    return false;
                }





            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return false;

        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);

            if (s) {

                Toast.makeText(getApplicationContext(), "Welcome back", Toast.LENGTH_SHORT).show();

                Intent toLikes = new Intent(context, ManageLikes.class);
                startActivity(toLikes);
                finish();

            }


        }
    }


    public class PostTask extends AsyncTask<String, Void, String> {

        private Exception exception;
        Context context;

        private PostTask(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                UserL2C newUser = new UserL2C(db.getUser(user_id).getfName(),
                                                db.getUser(user_id).getlName(),
                                                    user_id, db.getUser(user_id).getUserAbout(),
                                                        db.getUser(user_id).getUserDOB(),
                                                            db.getUser(user_id).getUserEmail(), db.getUser(user_id).getUserGender(), db.getUser(user_id).getUserLocation());


                Gson gson = new Gson();
                String json = gson.toJson(newUser);

                okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);

                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("http://lowcost-env.8jm8a7kdcg.us-west-2.elasticbeanstalk.com/webapi/users/addUser")
                        .post(body)
                        .build();

                okhttp3.Response response = client.newCall(request).execute();
                return response.toString();



            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

//            Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Added User Successfully", Toast.LENGTH_SHORT).show();

//            Move to the main activity
            Intent toSettings = new Intent(context, Settings.class);
            startActivity(toSettings);
            finish();
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.updateNextButton){
            try {
//            Send the data to the local database
                db = MySQLiteHelper.getInstance(getApplicationContext());

                firstNameUpdate = (EditText) findViewById(R.id.editTextFirstNameUpdate);
                lastNameUpdate = (EditText) findViewById(R.id.editTextLastNameUpdate);
                locationUpdate = (EditText) findViewById(R.id.editTextlocationUpdate);
                emailUpdate = (EditText) findViewById(R.id.editTextEmailUpdate);
                rg = (RadioGroup) findViewById(R.id.radioGenderUpdate);
                String gender = ((RadioButton) findViewById(rg.getCheckedRadioButtonId())).getText().toString();
                dobUpdate = (EditText) findViewById(R.id.editTextDOBUpdate);
                aboutUpdate = (EditText) findViewById(R.id.editTextAboutUpdate);

                User updateUser = new User(user_id, firstNameUpdate.getText().toString(), lastNameUpdate.getText().toString(),
                        locationUpdate.getText().toString(), emailUpdate.getText().toString(), gender,
                        dobUpdate.getText().toString(), aboutUpdate.getText().toString());


                db.updateUser(updateUser);

                new PostTask(this).execute();

            }catch(Exception e){
                Toast.makeText(this, "Error in field!" + e, Toast.LENGTH_LONG);
            }
        }
    }
}
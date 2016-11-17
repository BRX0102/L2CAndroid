package edu.csumb.brogrammers.likestochill;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import edu.csumb.brogrammers.likestochill.RetroFitRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Peter on 11/16/2016.
 */

public class RetroFitTestUserClass extends AppCompatActivity {

    /*String BASE_URL = "http://likestochill.heidyzqyc2.us-west-2.elasticbeanstalk.com/webapi";

    public void testRetroFit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroFitRest service = retrofit.create(RetroFitRest.class);

        User user = new User();

        Call<User> call = service.createUser(user);
        call.enqueue(new Callback<User>() {
                         @Override
                         public void onResponse(Call<User> call, Response<User> response) {

                         }

                         @Override
                         public void onFailure(Call<User> call, Throwable t) {

                         }
        });

    }*/

}

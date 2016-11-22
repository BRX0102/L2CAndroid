package edu.csumb.brogrammers.likestochill;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Peter on 11/16/2016.
 */

public class RetroFitTestUserClass extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
    }
//
//    String BASE_URL = "http://lowcost-env.8jm8a7kdcg.us-west-2.elasticbeanstalk.com/webapi/";
//
//
//    public interface RestService {
//
//        @GET("users/{fName}")
//        Call<User> getUser(@Path("fName") String username);
//
//        @POST("/users/new")
//        Call<User> createUser(@Body User user);
//    }
//
//    public void testRetroFit(){
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        RestService service = retrofit.create(RestService.class);
//
//        Call<User> call = service.getUser("fName");
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Log.d("response", response.toString());
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.d("TAG", t.toString());
//            }
//        });
//
//    }


}


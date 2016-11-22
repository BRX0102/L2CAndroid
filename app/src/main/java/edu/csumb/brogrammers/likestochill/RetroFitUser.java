package edu.csumb.brogrammers.likestochill;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import edu.csumb.brogrammers.likestochill.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by Peter on 11/16/2016.
 */

public class RetroFitUser {

    @SerializedName("id")
    int mId;

    @SerializedName("name")
    String mName;

    public RetroFitUser(int id, String name ) {
        this.mId = id;
        this.mName = name;
    }
}



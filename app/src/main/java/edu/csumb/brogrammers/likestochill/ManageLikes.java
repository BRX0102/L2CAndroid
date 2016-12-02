package edu.csumb.brogrammers.likestochill;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ManageLikes extends AppCompatActivity implements OnItemClickListener, View.OnClickListener{

    okhttp3.OkHttpClient client;
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    Button addBtn;
    String user_id, movie_name;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_likes);

        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);

        //SharedPreferences sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        //user_id = sharedPref.getString("user_id", "DEFAULT");
        user_id = "1132758413445743";

        listView = (ListView) findViewById(R.id.listView);
        new GetMovies().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ViewGroup vg = (ViewGroup) view;
        TextView tv = (TextView) vg.findViewById(R.id.movieTxt);
        movie_name = tv.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to remove " + movie_name + "?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                new DeleteTask().execute();

                Toast.makeText(ManageLikes.this, "removed", Toast.LENGTH_LONG).show();

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onClick(View v) {

        Intent toSearch = new Intent(getBaseContext(), SearchForMovies.class);
        startActivity(toSearch);
        finish();
    }

    public class DeleteTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(String... urls) {
            try {

                Movie newMovie = new Movie(movie_name, user_id);
                Gson gson = new Gson();
                String json = gson.toJson(newMovie);

                client = new OkHttpClient();
                okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("http://lowcost-env.8jm8a7kdcg.us-west-2.elasticbeanstalk.com/webapi/movies/deleteLike/" + user_id + "/" + movie_name)
                        .delete()
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

        }
    }

    public class GetMovies extends AsyncTask<String, Void, Movie[]> {

        private Exception exception;

        @Override
        protected Movie[] doInBackground(String... urls) {
            try {
                okhttp3.OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new Request.Builder()
                        .url("http://lowcost-env.8jm8a7kdcg.us-west-2.elasticbeanstalk.com/webapi/movies/"+user_id)
                        .build();

                okhttp3.Response response = client.newCall(request).execute();
                String result = response.body().string();

                Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<Movie>>() {}.getType();
                Collection<Movie> movieListJson = gson.fromJson(result,collectionType);
                Movie[] userListOBJ = movieListJson.toArray(new Movie[movieListJson.size()]);

                return userListOBJ;

            } catch (IOException ioe) {
                ioe.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Movie[] s) {
            super.onPostExecute(s);

            String[] r = new String[s.length];
            for (int i =0; i < s.length; i++){
                r[i] = s[i].getMovieTitle();
            }

            ListAdapter adapter = new ArrayAdapter<String>(ManageLikes.this, R.layout.likes_list_item, R.id.movieTxt, r);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(ManageLikes.this);
        }
    }
}
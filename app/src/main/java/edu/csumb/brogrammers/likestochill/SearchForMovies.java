package edu.csumb.brogrammers.likestochill;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SearchForMovies extends AppCompatActivity implements OnClickListener{
    Button submitTitleBtn, likeBtn, backBtn;
    TextView textViewOmdbResponse;
    EditText editTextMovieName;
    String url, title;
    ProgressDialog pd;
    String user_id;
    okhttp3.OkHttpClient client;
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_for_movies);

        SharedPreferences sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = sharedPref.getString("user_id", "DEFAULT");
        //user_id = "1132758413445743";

        submitTitleBtn = (Button) findViewById(R.id.submitTitleBtn);
        submitTitleBtn.setOnClickListener(this);

        likeBtn = (Button) findViewById(R.id.likeBtn);
        likeBtn.setVisibility(View.INVISIBLE);
        likeBtn.setOnClickListener(this);

        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);

        textViewOmdbResponse = (TextView) findViewById(R.id.textViewOmdbResponse);
        editTextMovieName = (EditText) findViewById(R.id.editTextMovieName);
    }

    @Override
    public void onBackPressed() {
        Intent toManageLikes = new Intent(this, ManageLikes.class);
        startActivity(toManageLikes);
        finish();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.likeBtn){

            String liked = title + " added";
            new PostTask().execute();
            textViewOmdbResponse.setText(liked);
            likeBtn.setVisibility(View.INVISIBLE);
            editTextMovieName.setText("");
            return;
        }
        else if (v.getId() == R.id.backBtn){
            Intent toManageLikes = new Intent(this, ManageLikes.class);
            startActivity(toManageLikes);
            finish();
        }
        else if (v.getId() == R.id.submitTitleBtn) {
            url = "http://www.omdbapi.com/?t=" + editTextMovieName.getText().toString() + "&type=movie&y=&plot=short&r=json";
            new JsonTask().execute(url);



        }
    }

    private class JsonTask extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

            super.onPreExecute();

            pd = new ProgressDialog(SearchForMovies.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String jsonResult) {
            super.onPostExecute(jsonResult);
            if (pd.isShowing()){
                pd.dismiss();
            }

            String year = "";
            String plot = "";
            try {
                JSONObject jObject = new JSONObject(jsonResult);
                title = jObject.getString("Title");
                year = jObject.getString("Year");
                plot = jObject.getString("Plot");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            textViewOmdbResponse.setText(Html.fromHtml("<b>" + "Title: " + "</b>" + title + "<p><b>"
                    + "Year: " + "</b>" + year + "<p><b>" + "Plot: " + "</b>" + plot));

            new getIfExists(user_id,title).execute();




        }
    }

    public class getIfExists extends AsyncTask<Void, Void, Boolean> {

        private Exception exception;

        String userId;
        String movieTitle;

        private getIfExists(String userId, String movieTitle) {
            this.userId = userId;
            this.movieTitle = movieTitle;

        }


        @Override
        protected Boolean doInBackground(Void... voids) {

            try {



                okhttp3.OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new Request.Builder()
                        .url("http://lowcost-env.8jm8a7kdcg.us-west-2.elasticbeanstalk.com/webapi/movies/"+userId)
                        .build();

                okhttp3.Response response = client.newCall(request).execute();
                String result = response.body().string();

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<Movie>>() {}.getType();
                Collection<Movie> userLikeListJson = gson.fromJson(result,collectionType);
                Movie[] userMovieList = userLikeListJson.toArray(new Movie[userLikeListJson.size()]);

                for (int i = 0; i < userMovieList.length; i++){
                    if (userMovieList[i].getMovieTitle().equals(movieTitle)) {
                        return true;
                    }
                }
                return false;


            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return false;

        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);

            if (s) {

//                Toast.makeText(SearchForMovies.this, "Already liked", Toast.LENGTH_LONG).show();
                Toast.makeText(SearchForMovies.this, "Already liked", Toast.LENGTH_LONG).show();
                likeBtn.setVisibility(View.INVISIBLE);


            } else {
                Toast.makeText(SearchForMovies.this, "You can like it", Toast.LENGTH_LONG).show();
                likeBtn.setVisibility(View.VISIBLE);
            }
        }
    }

    public class PostTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(String... urls) {
            try {
                Movie newMovie = new Movie(title, user_id);
                Gson gson = new Gson();
                String json = gson.toJson(newMovie);

                client = new OkHttpClient();
                okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("http://lowcost-env.8jm8a7kdcg.us-west-2.elasticbeanstalk.com/webapi/movies/addLike")
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
        }
    }
}
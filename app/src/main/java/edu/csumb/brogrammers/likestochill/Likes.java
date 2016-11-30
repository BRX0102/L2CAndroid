package edu.csumb.brogrammers.likestochill;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/*
*  frozen id: tt2294629
*  fake rapunzel id: tt0398286
*  barbie id: tt1092053
*  sleepy lady id: tt0053285
*
*
* */

public class Likes extends AppCompatActivity implements OnItemClickListener, View.OnClickListener{

    okhttp3.OkHttpClient client;
    MediaType JSON;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.likes);

        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);

        String [] movies = getMovies();
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.likes_list_item, R.id.movieTxt, movies);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(Likes.this);

    }


    public String[] getMovies() { // need another call to db here to populate listview

        // new GetMovies().execute();

        return new String[]{"Barbie as the Island Princess", "frozen", "tangled"};
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ViewGroup vg = (ViewGroup) view;
        TextView tv = (TextView) vg.findViewById(R.id.movieTxt);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to remove " + tv.getText().toString() + "?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                new DeleteTask().execute();

                Toast.makeText(Likes.this, "removed", Toast.LENGTH_LONG).show();

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
        Intent toSearchForMovies = new Intent(this, SearchForMovies.class);
        startActivity(toSearchForMovies);
    }

    public class DeleteTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(String... urls) {
            try {

//                String fName, String lName, int userId, String userAbout, String userDOB, String userEmail, String userGender, String userLocation

                //User newUser = new User("ppppppppppp", "kkkkkkkkkkkk", 10, "mmmmmmmmmmmmm", "1994-10-10", "uuuuuuuu@csumb.edu", "M", "2016 - 10 - 27");

                //String UserId, String MovieTitle
                Movie newMovie = new Movie("1", "frozen");
                Gson gson = new Gson();
                String json = gson.toJson(newMovie);


                client = new OkHttpClient();
                okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("http://lowcost-env.8jm8a7kdcg.us-west-2.elasticbeanstalk.com/webapi/movies/deleteLike/")
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
            /*
            TextView textView = (TextView) findViewById(R.id.myText);
            textView.setText(s);*/

        }



    }


    public class GetMovies extends AsyncTask<String, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(String... urls) {
            try {

//                String fName, String lName, int userId, String userAbout, String userDOB, String userEmail, String userGender, String userLocation

                //User newUser = new User("ppppppppppp", "kkkkkkkkkkkk", 10, "mmmmmmmmmmmmm", "1994-10-10", "uuuuuuuu@csumb.edu", "M", "2016 - 10 - 27");

                //String UserId, String MovieTitle
                Movie newMovie = new Movie("1", "frozen");
                Gson gson = new Gson();
                String json = gson.toJson(newMovie);


                client = new OkHttpClient();
                okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("http://lowcost-env.8jm8a7kdcg.us-west-2.elasticbeanstalk.com/webapi/movies/deleteLike/")
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


}
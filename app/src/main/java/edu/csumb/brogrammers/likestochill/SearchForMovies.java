package edu.csumb.brogrammers.likestochill;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sean on 11/13/2016.
 */

public class SearchForMovies extends AppCompatActivity implements OnClickListener{
    Button submitTitleBtn, submitIdBtn;
    TextView textViewOmdbResponse;
    EditText editTextMovieName, editTextMovieId;
    String url;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_for_movies);

        submitTitleBtn = (Button) findViewById(R.id.submitTitleBtn);
        submitTitleBtn.setOnClickListener(this);
        submitIdBtn = (Button) findViewById(R.id.submitIdBtn);
        submitIdBtn.setOnClickListener(this);
        textViewOmdbResponse = (TextView) findViewById(R.id.textViewOmdbResponse);
        editTextMovieName = (EditText) findViewById(R.id.editTextMovieName);
        editTextMovieId = (EditText) findViewById(R.id.editTextMovieId);



    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submitTitleBtn)
            url = "http://www.omdbapi.com/?t=" + editTextMovieName.getText().toString() + "&type=movie&y=&plot=short&r=json";
        else if(v.getId() == R.id.submitIdBtn)
            url = "http://www.omdbapi.com/?i=" + editTextMovieId.getText().toString() + "&type=movie&y=&plot=short&r=json";


        new JsonTask().execute(url);
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
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

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
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
            textViewOmdbResponse.setText(result);
        }
    }
}

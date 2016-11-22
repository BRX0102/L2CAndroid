package edu.csumb.brogrammers.likestochill;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import edu.csumb.brogrammers.likestochill.User;
import com.facebook.FacebookSdk;

import org.w3c.dom.Text;

/**
 * Created by Bjon on 11/21/2016.
 */

public class TestingDeleteThis extends AppCompatActivity {

    public static User thisUser;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing_delete_this);

        Log.d("wagger",thisUser.getfName());

        TextView mTextView = (TextView)findViewById(R.id.textView3);

        //mTextView.setText(thisUser.getfName());
    }
}

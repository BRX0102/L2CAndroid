package edu.csumb.brogrammers.likestochill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MySQLiteHelper db;
    Button pre, nex;
    ViewSwitcher view;
    private Toast backtoast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pre = (Button) findViewById(R.id.prev);
        String no = "No chill";
        pre.setText(no);
        nex = (Button) findViewById(R.id.next);
        String yes = "Chill";
        nex.setText(yes);


        view = (ViewSwitcher) findViewById(R.id.viewSwitcher1);

        pre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                view.showNext();

            }
        });
        nex.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                view.showNext();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
                if(backtoast!=null&&backtoast.getView().getWindowToken()!=null) {
                    finish();
                } else
                    backtoast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
                    backtoast.show();
                }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent toHome = new Intent(this, MainActivity.class);
            startActivity(toHome);
            this.finish();
        } else if (id == R.id.myProfile) {
            Intent toProfile = new Intent(this, Settings.class);
            startActivity(toProfile);
            this.finish();
        } else if (id == R.id.searchPreferences) {
            Intent toSearch = new Intent(this, SearchPreferences.class);
            startActivity(toSearch);
            this.finish();
        } else if (id == R.id.addLikes) {
            Intent toLikes = new Intent(this, ManageLikes.class);
            startActivity(toLikes);
            this.finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

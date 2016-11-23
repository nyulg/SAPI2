package com.sapi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import static android.R.attr.fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback

{

    NavigationView navigationView;
    private SharedPreferences pref;
    FloatingActionButton faBtn;
    SupportMapFragment sMapFragment;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentTransaction main = getSupportFragmentManager().beginTransaction();
        main.replace(R.id.fragment_frame, new FeedFragment());
        main.commit();
        pref = getPreferences(0);
        sMapFragment=SupportMapFragment.newInstance();


        faBtn = (FloatingActionButton) findViewById(R.id.search);
        faBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }

    public void showFloatingActionButton() {
        faBtn.show();
    }

    public void hideFloatingActionButton() {
        faBtn.hide();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feed) {
            showFloatingActionButton();
            FeedFragment feedfragment = new FeedFragment();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_frame, feedfragment, feedfragment.getTag()).commit();
            // Handle the camera action
        } else if (id == R.id.nav_contacts) {

        } else if (id == R.id.nav_calendar) {
            showFloatingActionButton();
            CalendarFragment calendarfragment = new CalendarFragment();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_frame, calendarfragment, calendarfragment.getTag()).commit();

        } else if (id == R.id.nav_maps) {
            hideFloatingActionButton();
            MapFragment mapfragment = new MapFragment();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_frame, mapfragment, mapfragment.getTag()).commit();

        } else if (id == R.id.nav_notes) {

        } else if (id == R.id.nav_neptun) {
            hideFloatingActionButton();
            NeptunFragment neptunfragment = new NeptunFragment();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_frame, neptunfragment, neptunfragment.getTag()).commit();
                    sMapFragment.getMapAsync(this);

        } else if (id == R.id.nav_books) {

        } else if (id == R.id.nav_rent) {

        } else if (id == R.id.nav_mother) {

        } else if (id == R.id.nav_reg) {
            initFragment();
            hideFloatingActionButton();


            /*startActivity(new Intent(getApplicationContext(), Registration_Activity.class));*/
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /* @Override
    public void onFragmentInteraction(Uri uri) {

    } */

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private void initFragment(){
       Fragment fragment;
        if(pref.getBoolean(Constants.IS_LOGGED_IN,false)){
            fragment = new ProfileFragment();
        }else {
            fragment = new LoginFragment();
        }
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}

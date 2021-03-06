package com.sapi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import static android.R.attr.fragment;
import static android.R.attr.toolbarStyle;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OnMapReadyCallback, LocationListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener

{

    NavigationView navigationView;
    private SharedPreferences pref;
    FloatingActionButton faBtn;
    SupportMapFragment sMapFragment;
    int count = getFragmentManager().getBackStackEntryCount();
    public ImageView imgView;
    public ImageView map_list;
    public ImageView map_search;
    public EditText map_search_text;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgView= (ImageView) findViewById(R.id.back);
        imgView.setVisibility(View.GONE);
        map_list= (ImageView) findViewById(R.id.map_list);
        map_list.setVisibility(View.GONE);
        map_search= (ImageView) findViewById(R.id.map_search);
        map_search.setVisibility(View.GONE);
        map_search_text= (EditText) findViewById(R.id.map_search_text);
        map_search_text.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        FragmentTransaction main = getSupportFragmentManager().beginTransaction();
        main.replace(R.id.fragment_frame, new FeedFragment());
        main.commit();
        pref = getPreferences(0);
        sMapFragment=SupportMapFragment.newInstance();
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).addApi(LocationServices.API)
                .addApi(ActivityRecognition.API).build();
        client.connect();

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
            if (count == 0) {
                super.onBackPressed();
            } else {
                getFragmentManager().popBackStack();
            }
        }
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
            hideFloatingActionButton();
            ContactsFragment contactsFragment = new ContactsFragment();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_frame, contactsFragment, contactsFragment.getTag()).commit();

        } else if (id == R.id.nav_calendar) {
            hideFloatingActionButton();
            CalendarFragment calendarfragment = new CalendarFragment();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_frame, calendarfragment, calendarfragment.getTag()).commit();

        } else if (id == R.id.nav_maps) {
            hideFloatingActionButton();
            MapFragment mapfragment = new MapFragment();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_frame, mapfragment, mapfragment.getTag()).commit();

        } else if (id == R.id.nav_notes) {
            hideFloatingActionButton();
            NoteFragment noteFragment = new NoteFragment();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_frame, noteFragment, noteFragment.getTag()).commit();

        } else if (id == R.id.nav_neptun) {
            hideFloatingActionButton();
            NeptunFragment neptunfragment = new NeptunFragment();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_frame, neptunfragment, neptunfragment.getTag()).commit();

        } else if (id == R.id.nav_market) {
            hideFloatingActionButton();
            MarketFragment marketFragment = new MarketFragment();
            fragmentManager.beginTransaction().replace(
                    R.id.fragment_frame, marketFragment, marketFragment.getTag()).commit();
            
        } else if (id == R.id.nav_reg) {
            initFragment();
            hideFloatingActionButton();


            /*startActivity(new Intent(getApplicationContext(), Registration_Activity.class));*/
        } else if (id == R.id.nav_settings) {

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
        android.support.v4.app.Fragment fragment;
        if(pref.getBoolean(Constants.IS_LOGGED_IN,false)){
            fragment = new ProfileFragment();
        }else {
            fragment = new LoginFragment();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        client.connect();

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    protected void startLocationUpdates() {
        LocationRequest mLocationRequest;
        mLocationRequest=new LocationRequest();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                client, mLocationRequest,this);
    }
}

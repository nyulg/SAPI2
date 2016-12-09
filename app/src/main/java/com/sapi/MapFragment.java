package com.sapi;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.MapValue;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.io.IOException;
import java.security.Provider;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.location.LocationManager.*;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;


public class MapFragment extends Fragment
        implements LocationListener, OnMapReadyCallback, LocationSource.OnLocationChangedListener,
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    public MapFragment() {
        // Required empty public constructor
    }

    private GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    public MapView mapView;
    public GoogleMap gmap;
    public Marker marker;
    FloatingActionButton net_FAB, nav_FAB, hide_LOC, my_location_FAB, eok_FAB, emk_FAB, ferencter_FAB;
    EditText location_tf;
    LatLng latlng;
    Location location;
    double latitude;
    double longitude;
    Polyline polylin;
    android.location.LocationListener locationlistener;
    public final static int MILLISECONDS_PER_SECOND = 1000;
    public final static int MINUTE = 60 * MILLISECONDS_PER_SECOND;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View map = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = (MapView) map.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mLocationRequest = new LocationRequest();
        mapView.getMapAsync(this);
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(ActivityRecognition.API)
                .build();
        mGoogleApiClient.connect();
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(MINUTE);
        mLocationRequest.setFastestInterval(15 * MILLISECONDS_PER_SECOND);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (location!=null){
            double c = location.getLatitude();
            double d = location.getLongitude();
            LatLng myloc = new LatLng(c, d);
            gmap.animateCamera(CameraUpdateFactory.newLatLng(myloc));
            gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, 16));
        }
        EditText LOC_search = (EditText) map.findViewById(R.id.search_location_text);
        LOC_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView LOC_search, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    if (marker != null) {
                        marker.remove();
                    }
                    EditText location_tf = (EditText) getView().findViewById(R.id.search_location_text);
                    String location = location_tf.getText().toString();
                    List<android.location.Address> addressList = null;
                    if (location != null || !location.equals("")) {
                        Geocoder geocoder = new Geocoder(getActivity());
                        try {
                            addressList = geocoder.getFromLocationName(location, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    android.location.Address address = addressList.get(0);
                    latlng = new LatLng(address.getLatitude(), address.getLongitude());
                    marker = gmap.addMarker(new MarkerOptions().position(latlng));
                    gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
                    InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.hideSoftInputFromWindow(LOC_search.getWindowToken(), 0);
                }
                return false;
            }
        });
            /*@Override
            public void onClick(View view) {
                if (marker != null) {
                    marker.remove();
                }
                EditText location_tf = (EditText) getView().findViewById(R.id.search_location_text);
                String location = location_tf.getText().toString();
                List<android.location.Address> addressList = null;
                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(getActivity());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                android.location.Address address = addressList.get(0);
                latlng = new LatLng(address.getLatitude(), address.getLongitude());
                marker = gmap.addMarker(new MarkerOptions().position(latlng));
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
            }
        });*/

        FloatingActionButton show_LOC = (FloatingActionButton) map.findViewById(R.id.show_loc);
        show_LOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFloatingActionButton();
            }
        });
        hide_LOC = (FloatingActionButton) map.findViewById(R.id.hide_loc);
        hide_LOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideFloatingActionButton();
            }
        });
        net_FAB = (FloatingActionButton) map.findViewById(R.id.net_fab);
        net_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng net = new LatLng(47.479160, 19.091105);
                if (marker != null) {
                    marker.remove();
                }
                marker = gmap.addMarker(new MarkerOptions().position(net).title("Nagyvárad téri Elméleti Tömb"));
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(net, 15));
                hideFloatingActionButton();

            }
        });
        eok_FAB = (FloatingActionButton) map.findViewById(R.id.eok_fab);
        eok_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng eok = new LatLng(47.481249, 19.078048);
                if (marker != null) {
                    marker.remove();
                }
                marker = gmap.addMarker(new MarkerOptions().position(eok).title("Elméleti Orvostudományi Központ"));
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(eok, 15));
                hideFloatingActionButton();

            }
        });
        emk_FAB = (FloatingActionButton) map.findViewById(R.id.emk_fab);
        emk_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng emk = new LatLng(47.510507, 19.006626);
                if (marker != null) {
                    marker.remove();
                }
                marker = gmap.addMarker(new MarkerOptions().position(emk).title("Egészségügyi Menedzserképző Központ"));
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(emk, 15));
                hideFloatingActionButton();

            }
        });
        ferencter_FAB = (FloatingActionButton) map.findViewById(R.id.ferencter_fab);
        ferencter_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng ferencter = new LatLng(47.481604, 19.073338);
                if (marker != null) {
                    marker.remove();
                }
                marker = gmap.addMarker(new MarkerOptions().position(ferencter).title("Ferenc tér"));
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(ferencter, 15));
                hideFloatingActionButton();

            }
        });

        nav_FAB = (FloatingActionButton) map.findViewById(R.id.nav_fab);
        nav_FAB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (polylin != null) {
                    polylin.remove();
                }
                if (marker == null) {
                    Snackbar.make(view, "Nem választottál célt!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    if (location != null) {
                        double c = location.getLatitude();
                        double d = location.getLongitude();
                        LatLng sourcePosition = new LatLng(c, d);
                        LatLng destPosition = marker.getPosition();
                        final Handler handler = new Handler() {
                            public void handleMessage(Message msg) {
                                try {
                                    Document doc = (Document) msg.obj;
                                    GMapV2Direction md = new GMapV2Direction();
                                    ArrayList<LatLng> directionPoint = md.getDirection(doc);
                                    PolylineOptions rectLine = new PolylineOptions().width(12).color(getActivity().getResources().getColor(R.color.colorPrimary));

                                    for (int i = 0; i < directionPoint.size(); i++) {
                                        rectLine.add(directionPoint.get(i));
                                    }
                                    polylin = gmap.addPolyline(rectLine);
                                    md.getDurationText(doc);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        new GMapV2DirectionAsyncTask(handler, sourcePosition, destPosition, GMapV2Direction.MODE_TRANSIT).execute();
                    }
                }
            }
        });
        my_location_FAB = (FloatingActionButton) map.findViewById(R.id.my_location_fab);
        my_location_FAB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (location != null) { // Check to ensure coordinates aren't null, probably a better way of doing this...
                    double c = location.getLatitude();
                    double d = location.getLongitude();
                    LatLng myloc = new LatLng(c, d);
                    gmap.animateCamera(CameraUpdateFactory.newLatLng(myloc));
                    gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, 16));
                }
            }
        });

        hideFloatingActionButton();

        return map;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMyLocationEnabled(true);
        gmap.getUiSettings().setMyLocationButtonEnabled(false);
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();
        locationlistener = new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location loc) {
                double latitude = loc.getLatitude();
                double longitude = loc.getLongitude();
                location = new Location("");
                location.setLatitude(latitude);
                location.setLongitude(longitude);
                return;
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String bestProvider) {
                LocationManager locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.INTERNET
                        }, 10);
                        return;
                    }
                    locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationlistener);
                } else {
                    locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationlistener);
                }
            }

            @Override
            public void onProviderDisabled(String s) {
                LocationManager locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    if (checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.INTERNET
                        }, 10);
                        return;
                    }
                    locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationlistener);
                } else {
                    locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationlistener);
                }
            }
        };
        location = locationManager.getLastKnownLocation(bestProvider);
        if (location == null) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
                return;
            }
            locationManager.requestLocationUpdates(bestProvider, 0, 0, locationlistener);
            return;
        } else {
            double c = location.getLatitude();
            double d = location.getLongitude();
            LatLng myloc = new LatLng(c, d);
            gmap.animateCamera(CameraUpdateFactory.newLatLng(myloc));
            gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, 16));
        }

        /*location = locationManager.getLastKnownLocation(GPS_PROVIDER);
        gmap = googleMap;
        /*LatLng bp = new LatLng(47.506340, 19.042851);
        gmap.animateCamera(CameraUpdateFactory.newLatLng(bp));
        marker = gmap.addMarker(new MarkerOptions().position(bp).title("Buda fckn Pest"));
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(bp, 12));
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10);
            return;
        }
        /*gmap.setMyLocationEnabled(true);
        startLocationUpdates();
        gmap.getUiSettings().setMyLocationButtonEnabled(false);
        double c = location.getLatitude();
        double d = location.getLongitude();
        LatLng myloc = new LatLng(c, d);
        gmap.animateCamera(CameraUpdateFactory.newLatLng(myloc));
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, 16));*/

    }


    public void showFloatingActionButton() {
        net_FAB.show();
        hide_LOC.show();
        eok_FAB.show();
        emk_FAB.show();
        ferencter_FAB.show();
    }

    public void hideFloatingActionButton() {
        net_FAB.hide();
        hide_LOC.hide();
        eok_FAB.hide();
        emk_FAB.hide();
        ferencter_FAB.hide();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onLocationChanged(Location loc) {
        double latitude = loc.getLatitude();
        double longitude = loc.getLongitude();
        location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return;

    }



    /*protected void route(LatLng sourcePosition, LatLng destPosition, String mode) {
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                try {
                    Document doc = (Document) msg.obj;
                    GMapV2Direction md = new GMapV2Direction();
                    ArrayList<LatLng> directionPoint = md.getDirection(doc);
                    PolylineOptions rectLine = new PolylineOptions().width(15).color(getActivity().getResources().getColor(R.color.navbar));

                    for (int i = 0; i < directionPoint.size(); i++) {
                        rectLine.add(directionPoint.get(i));
                    }
                    Polyline polylin = gmap.addPolyline(rectLine);md.getDurationText(doc);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            ;
        };

        new GMapV2DirectionAsyncTask(handler, sourcePosition, destPosition, GMapV2Direction.MODE_DRIVING).execute();
    }


    protected void route(LatLng sourcePosition, LatLng destPosition, String mode) {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        Location location = locationManager.getLastKnownLocation(GPS_PROVIDER);

        long c = (long) location.getLatitude();
        long d = (long) location.getLongitude();
        sourcePosition = new LatLng(c, d);
        destPosition = marker.getPosition();
        try {
            GMapV2Direction md = new GMapV2Direction();
            Document doc = md.getDocument(sourcePosition, destPosition,
                    GMapV2Direction.MODE_DRIVING);

            ArrayList<LatLng> directionPoint = md.getDirection(doc);
            PolylineOptions rectLine = new PolylineOptions().width(3).color(
                    Color.RED);

            for (int i = 0; i < directionPoint.size(); i++) {
                rectLine.add(directionPoint.get(i));
            }
            Polyline polylin = gmap.addPolyline(rectLine);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateGPSCoordinates() {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }*/

    protected void startLocationUpdates() {
        mLocationRequest = new LocationRequest();
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10);
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, (LocationListener) getActivity());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    return;
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10);
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

package com.sapi;

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
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.MapValue;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import android.view.View.OnClickListener;

import org.w3c.dom.Document;

import java.io.IOException;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.location.LocationManager.*;


public class MapFragment extends Fragment implements LocationListener, OnMapReadyCallback {
    public MapFragment() {
        // Required empty public constructor
    }
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    public MapView mapView;
    public GoogleMap gmap;
    public Marker marker;
    FloatingActionButton net_FAB, nav_FAB;
    EditText location_tf;
    LatLng latlng;
    Location location;
    double latitude;
    double longitude;
    LocationListener locationlistener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View map = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = (MapView) map.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        EditText LOC_search = (EditText) map.findViewById(R.id.searh_location_text);
        LOC_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (marker != null) {
                marker.remove();
                }
                EditText location_tf = (EditText) getView().findViewById(R.id.searh_location_text);
                String location = location_tf.getText().toString();
                List<android.location.Address> addressList = null;
                if (location!=null || !location.equals("")){
                    Geocoder geocoder = new Geocoder(getActivity());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            android.location.Address address = addressList.get(0);
            latlng= new LatLng (address.getLatitude(), address.getLongitude());
            marker = gmap.addMarker(new MarkerOptions().position(latlng));
            gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
            }
        });

        FloatingActionButton show_LOC = (FloatingActionButton) map.findViewById(R.id.show_loc);
        show_LOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFloatingActionButton();
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

        nav_FAB = (FloatingActionButton) map.findViewById(R.id.nav_fab);
        nav_FAB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    return;
                }
                location = locationManager.getLastKnownLocation(GPS_PROVIDER);
                /*location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);*/
                if (location == null) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (LocationListener) getActivity());

                } else {
                    //If everything went fine lets get latitude and longitude
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
                                Polyline polylin = gmap.addPolyline(rectLine);
                                md.getDurationText(doc);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    new GMapV2DirectionAsyncTask(handler, sourcePosition, destPosition, GMapV2Direction.MODE_DRIVING).execute();
                }
                /*double c = location.getLatitude();
                double d = location.getLongitude();*/
            }
        });

        hideFloatingActionButton();

        return map;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(GPS_PROVIDER);
        gmap = googleMap;
        /*LatLng bp = new LatLng(47.506340, 19.042851);
        gmap.animateCamera(CameraUpdateFactory.newLatLng(bp));
        marker = gmap.addMarker(new MarkerOptions().position(bp).title("Buda fckn Pest"));
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(bp, 12));*/
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        gmap.setMyLocationEnabled(true);
        double c=location.getLatitude();
        double d=location.getLongitude();
        LatLng myloc = new LatLng(c,d);
        gmap.animateCamera(CameraUpdateFactory.newLatLng(myloc));
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, 10));

    }

    public void showFloatingActionButton() {
        net_FAB.show();
    }

    public void hideFloatingActionButton() {
        net_FAB.hide();
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
    public void onLocationChanged(Location location) {

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
}

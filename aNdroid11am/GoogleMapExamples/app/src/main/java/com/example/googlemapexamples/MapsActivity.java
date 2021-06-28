package com.example.googlemapexamples;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


@SuppressWarnings("deprecation")
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback ,
        LocationListener, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener
{

    private GoogleMap mMap;
     LocationManager manager;
    Location mlastlocation;
    GoogleApiClient googleApiClient;
    Marker mCurrentLocation;
    LocationRequest locationRequest;
    EditText locationedit;
    Button searchlocation;
    String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET};
    int locationcoed = 212;
    int permisssioncode =123;
    double startinglongitude,startinglatitude,endinglongitude,endinglatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        locationedit = findViewById(R.id.cityname);
        searchlocation = findViewById(R.id.showdistancbtn);
         statusCheck();
        searchlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String citynametext = locationedit.getText().toString().trim();

                searchlocations(citynametext);
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(perms,permisssioncode);
            }
        }


    }

    private void searchlocations(String citynametext) {

        List<Address> addressList = null;

        if(citynametext != null || citynametext.equals(""))
        {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList=geocoder.getFromLocationName(citynametext,4);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            endinglatitude = address.getLatitude();
            endinglongitude = address.getLongitude();
            LatLng latLng = new LatLng(endinglatitude,endinglongitude);
            mMap.addMarker(new MarkerOptions().position(latLng));

            float[] result = new float[1];

            Location.distanceBetween(startinglatitude,startinglongitude,endinglatitude,endinglongitude,result);
            Log.e("MapsActivity",result[0]+"");
            float distance = result[0];
//            float distanefloat = Float.parseFloat(String.valueOf(distance));

            float distanckm = distance/1000;
            Toast.makeText(this, "Distance is "+distanckm+" kilometers", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(perms,permisssioncode);
            }else
            {
                buildgoogleapiclient();
                mMap.setMyLocationEnabled(true);


            }

        }else
        {
            buildgoogleapiclient();
            mMap.setMyLocationEnabled(true);

        }
        LatLng newplace = new LatLng(startinglatitude, startinglongitude);
        mMap.addMarker(new MarkerOptions().position(newplace).title("Current location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newplace));
    }

    private synchronized void buildgoogleapiclient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        googleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(2000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mlastlocation = location;
        if(mCurrentLocation != null)
        {
            mCurrentLocation.remove();
        }
        startinglatitude = location.getLatitude();
        startinglongitude = location.getLongitude();

        List<Address> addressList = null;


            Geocoder geocoder = new Geocoder(this);
            try {
                addressList=geocoder.getFromLocation(startinglatitude,startinglongitude,1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        String zipcode = addressList.get(0).getPostalCode();
            String citybname = addressList.get(0).getSubLocality();
            String city = addressList.get(0).getLocality();
        Toast.makeText(this, "mylocation "+city+" \n  code => "+zipcode + "\n subcity => "+citybname, Toast.LENGTH_LONG).show();
        LatLng latLngs = new LatLng(startinglatitude,startinglongitude);
        MarkerOptions markerOptionss = new MarkerOptions();
        markerOptionss.position(latLngs);
        markerOptionss.title(city);
        markerOptionss.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mCurrentLocation = mMap.addMarker(markerOptionss);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngs));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        if(googleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,  this);


        }
    }

    public void statusCheck() {
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
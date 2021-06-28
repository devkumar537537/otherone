@file:Suppress("DEPRECATION")

package com.example.mymapexampleagain

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{



    lateinit var locationedit: EditText
    lateinit var searchlocation: Button
    lateinit var mMap: GoogleMap
  lateinit  var manager: LocationManager
  lateinit  var mlastlocation: Location
    lateinit  var googleApiClient: GoogleApiClient
    var mCurrentLocation: Marker? = null
    lateinit var locationRequest: LocationRequest
    var perms = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET)
    var locationcoed = 212
    var permisssioncode = 123
    var startinglongitude = 0.0
    var startinglatitude = 0.0
    var endinglatitude = 0.0
    var endinglongitude = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationedit = findViewById(R.id.cityname)
        searchlocation = findViewById(R.id.showdistancbtn)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(perms,permisssioncode)
            }
        }
        searchlocation.setOnClickListener(View.OnClickListener {
            val citynametext = locationedit.text.toString().trim()
            searchlocations(citynametext)
        })
    }

    private fun searchlocations(citynametext: String) {
        var addressList: List<Address>? = null
        if (citynametext != null || citynametext == "") {
            val geocoder = Geocoder(this)
            try {
                addressList = geocoder.getFromLocationName(citynametext, 1)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val address = addressList!![0]
            endinglatitude = address.latitude
            endinglongitude = address.longitude
            val latLng = LatLng(endinglatitude, endinglongitude)

            mMap!!.addMarker(MarkerOptions().position(latLng))

            val result = FloatArray(1)

            Location.distanceBetween(startinglatitude, startinglongitude, endinglatitude, endinglongitude, result)
            Log.e("MapsActivity", result[0].toString() + "")
            val distance = result[0]
            val distanckm = distance / 1000
            Toast.makeText(this, "Distance is $distanckm kilometers", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(perms, permisssioncode)
            } else {
                buildgoogleapiclient()
                mMap!!.isMyLocationEnabled = true
            }
        } else {
            buildgoogleapiclient()
            mMap!!.isMyLocationEnabled = true
        }
        val newplace = LatLng(startinglatitude, startinglongitude)
        mMap!!.addMarker(MarkerOptions().position(newplace).title("Currentlocation"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(newplace))
// Add a marker in Sydney and move the camera
// LatLng sydney = new LatLng(30.7333, 76.7794);
// mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
// mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Synchronized
    private fun buildgoogleapiclient() {
        googleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build()
        googleApiClient.connect()
    }

    override fun onLocationChanged(location: Location) {
        mlastlocation = location
        if (mCurrentLocation != null) {
            mCurrentLocation!!.remove()
        }

        startinglatitude = location.latitude
        startinglongitude = location.longitude

        var addressList: List<Address>? = null
        val geocoder = Geocoder(this)
        try {
            addressList = geocoder.getFromLocation(startinglatitude, startinglongitude, 1)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val zipcode = addressList!![0].postalCode

        val citybname = addressList[0].subLocality
        val city = addressList[0].locality
        Toast.makeText(this, "mylocation $city \n  code => $zipcode\n subcity => $citybname", Toast.LENGTH_LONG).show()
        val latLngs = LatLng(startinglatitude, startinglongitude)
        val markerOptionss = MarkerOptions()
        markerOptionss.position(latLngs)
        markerOptionss.title(city)
        markerOptionss.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        mCurrentLocation = mMap!!.addMarker(markerOptionss)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLngs))
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(11f))
        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this)
        }
    }

    override fun onConnected(p0: Bundle?) {
        locationRequest = LocationRequest()
        locationRequest.setInterval(2000)
        locationRequest.setFastestInterval(2000)
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this)
        }
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }
}
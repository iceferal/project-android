package com.iceferal.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

//    private static final String TAG = com.example.currentplacedetailsonmap.MapsActivityCurrentPlace.class.getSimpleName();
    private DrawerLayout drawer;
    private GoogleMap map;
    private CameraPosition cameraPosition;

    private PlacesClient placesClient;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;
    private Location lastKnownLocation;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private static final int M_MAX_ENTRIES = 5;
    private String[] likelyPlaceNames;
    private String[] likelyPlaceAddresses;
    private List[] likelyPlaceAttributions;
    private LatLng[] likelyPlaceLatLngs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.home_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.menu_opener, R.string.menu_closer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



//^^ up menu ^^

        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);        }

        Places.initialize(getApplicationContext(), getString(R.string.maps_api_key));
        placesClient = Places.createClient(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
}
//menu function
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drive:
                Toast.makeText(this, "drive kurwa", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.home:
                Toast.makeText(this, "home bitch!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.friends:
                Toast.makeText(this, "friends!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.chat:
                Toast.makeText(this, "chat!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.faq:
                Toast.makeText(this, "faq!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.help:
                Toast.makeText(this, "help kurwa mnie!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                Toast.makeText(this, "Wylogowano!", Toast.LENGTH_SHORT).show();
                userLogout();
                finish();
                return true;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }    }


//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        this.map = map;
//        this.map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//
//            @Override
//            public View getInfoWindow(Marker arg0) {
//                return null;            }
//
//            @Override
//            public View getInfoContents(Marker marker) {
//                View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_contents,
//                        (FrameLayout) findViewById(R.id.mapView), false);
//
//                TextView title = infoWindow.findViewById(R.id.title);
//                title.setText(marker.getTitle());
//
//                TextView snippet = infoWindow.findViewById(R.id.snippet);
//                snippet.setText(marker.getSnippet());
//
//                return infoWindow;            }
//        });
//
//        getLocationPermission();
//        updateLocationUI();
//        getDeviceLocation();
//    }

    private void updateLocationUI() {
    }

    private void getLocationPermission() {
    }

    private void getDeviceLocation() {

    }

    public void userLogout() {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();    }
}
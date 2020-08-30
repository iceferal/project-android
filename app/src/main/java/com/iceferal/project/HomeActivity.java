package com.iceferal.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//^^ up menu ^^
        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.home_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.menu_opener, R.string.menu_closer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

// maps location

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }
        else {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if(location != null) {
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng).title("tutaj kufa");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });

    }

    //menu function
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drive:
//                Toast.makeText(this, "drive", Toast.LENGTH_SHORT).show();
                Intent drive_intent = new Intent(HomeActivity.this, DriveActivity.class);
                startActivity(drive_intent);
                return true;
            case R.id.home:
                Toast.makeText(this, "home!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.friends:
//                Toast.makeText(this, "friends!", Toast.LENGTH_SHORT).show();
                Intent friends_intent = new Intent(HomeActivity.this, FriendsActivity.class);
                startActivity(friends_intent);
                return true;
            case R.id.chat:
//                Toast.makeText(this, "chat!", Toast.LENGTH_SHORT).show();
                Intent chat_intent = new Intent(HomeActivity.this, ChatActivity.class);
                startActivity(chat_intent);
                return true;
            case R.id.faq:
//                Toast.makeText(this, "faq!", Toast.LENGTH_SHORT).show();
                Intent faq_intent = new Intent(HomeActivity.this, FaqActivity.class);
                startActivity(faq_intent);
                return true;
            case R.id.help:
//                Toast.makeText(this, "help mnie!", Toast.LENGTH_SHORT).show();
                Intent help_intent = new Intent(HomeActivity.this, HelpActivity.class);
                startActivity(help_intent);
                return true;
            case R.id.profil:
//                Toast.makeText(this, "profil!", Toast.LENGTH_SHORT).show();
                Intent profil_intent = new Intent(HomeActivity.this, ProfilActivity.class);
                startActivity(profil_intent);
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
            super.onBackPressed();        }
    }

    public void userLogout() {
        LoginActivity.disconnectFromFacebook();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();    }
}
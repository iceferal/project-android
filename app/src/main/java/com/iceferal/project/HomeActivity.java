package com.iceferal.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;



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
            case R.id.profil:
                Toast.makeText(this, "profil kurwa!", Toast.LENGTH_SHORT).show();
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
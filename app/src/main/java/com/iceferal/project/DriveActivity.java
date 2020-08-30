package com.iceferal.project;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DriveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);

        Toolbar toolbar = (Toolbar) findViewById(R.id.drive_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DRIVE");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
package com.iceferal.project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.iceferal.project.models.User;
import com.squareup.picasso.Picasso;

public class ProfilActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner_moto;
    int style = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.profil_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imageLogo = (ImageView) findViewById(R.id.imageLogo);

        Picasso.get().load(User.getImage()).resize(250, 250).into(imageLogo);
        TextView name_text = (TextView) findViewById(R.id.name_text);
        TextView surname_text = (TextView) findViewById(R.id.surname_text);
        TextView login_text = (TextView) findViewById(R.id.login_text);
        TextView email_text = (TextView) findViewById(R.id.email_text);
        TextView hasło_text = (TextView) findViewById(R.id.hasło_text);

        name_text.setText(User.getName());
        surname_text.setText(User.getSurname());
        login_text.setText(User.getLogin());
        email_text.setText(User.getEmail());
        hasło_text.setText("haslo");

        TextView moto_text = (TextView) findViewById(R.id.moto_text);
        TextView model_text = (TextView) findViewById(R.id.model_text);
        TextView rocznik_text = (TextView) findViewById(R.id.rocznik_text);
//        TextView styl_text = (TextView) findViewById(R.id.styl_text);

        moto_text.setText("Honda");
        model_text.setText("CBR 600 F4i");
        rocznik_text.setText("2002");
//        styl_text.setText("turystyczny");

        spinner_moto = (Spinner) findViewById(R.id.spinner_moto);
        spinner_moto.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0) {
            style = 0;      }
        if(position == 1) {
            style = 1;  }
        if(position == 2) {
            style = 2;      }
        if(position == 3) {
            style = 3;  }
        if(position == 4) {
            style = 4;  }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        }

    public void changePic(View view) {
        Toast.makeText(ProfilActivity.this, "Ta funkcja z czasem.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ProfilActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void daneBtn(View view) {
        Intent intent = new Intent(ProfilActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void motoBtn(View view) {
        Intent intent = new Intent(ProfilActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void usunBtn(View view) {
        Intent intent = new Intent(ProfilActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
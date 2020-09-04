package com.iceferal.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HelpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String state[] = null;
    Spinner spinner_no1;
    Spinner spinner_no2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        final TextView textHelp = (TextView) findViewById(R.id.nametext);
        Toolbar toolbar = (Toolbar) findViewById(R.id.help_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Help");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_no1 = (Spinner) findViewById(R.id.spinner_no1);
        spinner_no2 = (Spinner) findViewById(R.id.spinner_no2);
        spinner_no1.setOnItemSelectedListener(this);

//        Spinner spinner_no1 = (Spinner) findViewById(R.id.spinner_no1);
//        ArrayAdapter<String> adapter_no1 = new ArrayAdapter<String>(HelpActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_no1));
//        adapter_no1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_no1.setAdapter(adapter_no1);


//        Spinner spinner_no2 = (Spinner) findViewById(R.id.spinner_no2);
//        ArrayAdapter<String> adapter_no2 = new ArrayAdapter<String>(HelpActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_no2));
//        adapter_no2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_no2.setAdapter(adapter_no2);

        final RelativeLayout layout_no1 = (RelativeLayout) findViewById(R.id.spinner1);
        final RelativeLayout layout_no2 = (RelativeLayout) findViewById(R.id.spinner2);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    layout_no1.setVisibility(View.GONE);
                    layout_no2.setVisibility(View.GONE);
                    textHelp.setVisibility(View.VISIBLE);    }
                else {
                    layout_no1.setVisibility(View.VISIBLE);
                    layout_no2.setVisibility(View.VISIBLE);
                    textHelp.setVisibility(View.GONE);       }
            }
        });



    }

    public void HelpClick(View view) {
        String uklad = spinner_no1.getSelectedItem().toString();
        String awaria = spinner_no2.getSelectedItem().toString();

        Toast.makeText(HelpActivity.this, "Wysłano prośbę o pomoc.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HelpActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0) {
            state = new String[]{"rozładowany akumulator", "brak ładowania", "przepalony bezpiecznik", "awaria rozrusznika"};      }
        if(position == 1) {
            state = new String[]{"uszkodzone tarcze", "uszkodzone klocki", "zerwana linka hamulcowa", "brak płynu hamulcowego", "złamana klamka hamulca"};  }
        if(position == 2) {
            state = new String[]{"poluzowana zębatka", "uszkodzona zębatka", "uszkodzony łańcuch", "przebita opona"};      }
        if(position == 3) {
            state = new String[]{"awaria termostatu", "uszkodzona chłodnica", "awaria pompy wody", "wyciek płynu"};  }
        if(position == 4) {
            state = new String[]{"brak paliwa", "nieszczelny przewód paliwowy", "uszkodzona pompa paliwa", "zatkany filtr paliwa"};      }
        if(position == 5) {
            state = new String[]{"uszkodzony przedni teleskop", "uszkodzony tylny amortyzator"};  }
        if(position == 6) {
            state = new String[]{"wyciek oleju", "brak oleju", "uszkodzona uszczelka", "pęknięta komora silnika", "wzrost temperatury silnika"};  }
        ArrayAdapter<String> addicted = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, state);
        spinner_no2.setAdapter(addicted);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}

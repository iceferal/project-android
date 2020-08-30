package com.iceferal.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        final TextView textHelp = (TextView) findViewById(R.id.nametext);
        Toolbar toolbar = (Toolbar) findViewById(R.id.help_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Help");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Spinner spinner_no1 = (Spinner) findViewById(R.id.spinner_no1);
        ArrayAdapter<String> adapter_no1 = new ArrayAdapter<String>(HelpActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_no1));
        adapter_no1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_no1.setAdapter(adapter_no1);

        final Spinner spinner_no2 = (Spinner) findViewById(R.id.spinner_no2);
        ArrayAdapter<String> adapter_no2 = new ArrayAdapter<String>(HelpActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_no2));
        adapter_no2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_no2.setAdapter(adapter_no2);

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
        Toast.makeText(HelpActivity.this, "Wysłano prośbę o pomoc.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HelpActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

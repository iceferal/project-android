package com.iceferal.project;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText login, email, pass, repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login = findViewById(R.id.login);
        pass = findViewById(R.id.pass);
        repass = findViewById(R.id.rePass);
        email = findViewById(R.id.email);

    }

    public void klik(View view) {
        boolean status = false;
        String log = login.getText().toString();
        String password = pass.getText().toString();
        String repassword = repass.getText().toString();
        String mail = email.getText().toString();

        if (TextUtils.isEmpty(log)) {
            login.setError("Pole haslo nie moze byc puste");
            status = false;        }
        if (TextUtils.isEmpty(password)) {
            pass.setError("Pole haslo nie moze byc puste");
            status = false;         }
        if (TextUtils.isEmpty(repassword)) {
            repass.setError("Pole haslo nie moze byc puste");
            status = false;        }
        if (TextUtils.isEmpty(mail)) {
            email.setError("Pole haslo nie moze byc puste");
            status = false;         }


    }
}

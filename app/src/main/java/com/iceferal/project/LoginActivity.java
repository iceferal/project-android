package com.iceferal.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_text);
        password = findViewById(R.id.pass_text);
//        Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.animation_alpha);

        TextView textView = findViewById(R.id.textView);
        String text = "Jeśli jeszcze nie masz konta: zarejestruj sie";

        SpannableString span = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint kolor) {
                super.updateDrawState(kolor);
                kolor.setColor(Color.BLUE);
                kolor.setUnderlineText(false);
            }
        };

        span.setSpan(clickableSpan, 30, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(span);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
// logowanie
    public void loginClick(View view) {
        boolean status = true;
        String log = login.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(log)) {
            login.setError("Pole haslo nie moze byc puste");
            status = false;        }
        if (TextUtils.isEmpty(pass)) {
            password.setError("Pole haslo nie moze byc puste");
            status = false;        }

        if (status) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }

//    logowanie fb
    public void fbClick(View view) {
        Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.animation_alpha);
        view.startAnimation(animAlpha);
    }

//    logowanie google
    public void googleClick(View view) {
        Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.animation_alpha);
        view.startAnimation(animAlpha);
    }
}

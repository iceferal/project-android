package com.iceferal.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.iceferal.project.POJO.UserService;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private LoginButton fbButton;
    private EditText login, password;
    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private SignInButton googleSignInButton;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 0;
    UserService userService = UserService.retrofit.create(UserService.class);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();

        login = findViewById(R.id.login_text);
        password = findViewById(R.id.pass_text);
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

//        fb button
        fbButton = findViewById(R.id.fb);
//        callbackManager = CallbackManager.Factory.create();
        facebookLogin();

        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile", "user_birthday"));
            }
        });

//        google login
        googleSignInButton = findViewById(R.id.google);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
}
//    public void printHashKey() {
//        try {
////            PackageInfo info = getPackageManager().getPackageInfo("com.iceferal.project", PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));         }        }
//        catch (PackageManager.NameNotFoundException e) {
//        }
//        catch (NoSuchAlgorithmException e) {
//        }   }

//    private void checkLoginStatus() {
//        if(AccessToken.getCurrentAccessToken() != null) {
//            loadUserProfile(AccessToken.getCurrentAccessToken());
//        }    }

    // logowanie
    public void loginClick(View view) {
        boolean status = true;
        String log = login.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(log)) {
            login.setError("Pole login nie moze byc puste");
            status = false;        }
        if (TextUtils.isEmpty(pass)) {
            password.setError("Pole haslo nie moze byc puste");
            status = false;        }

        Call<String> loginCheck = userService.checkLogin(log);
        loginCheck.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String loginCheck = response.body();
                if(loginCheck == "false") {
                    login.setError("podany login nie istnieje");                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Serwer chwilowo nie odpowiada, spróbuj pozniej.", Toast.LENGTH_SHORT).show();            }
        });

        if (status) {
            String check = log + "_" + pass;
            Call<String> checkIt = userService.checkit(check);
            checkIt.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String checkIt = response.body();
                if(checkIt == "false") {
                    Toast.makeText(LoginActivity.this, "login lub hasło są niewłaściwe.", Toast.LENGTH_SHORT).show();                }
                if(checkIt == "true") {
                   Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();               }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Serwer chwilowo nie odpowiada, spróbuj pozniej.", Toast.LENGTH_SHORT).show();
//                furtka dla offline rest api:
//                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                startActivity(intent);
//                finish();
//                koniec furtki
                }
        }); }
    }

//    logowanie fb
//    public void fbClick(View view) {
//        Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.animation_alpha);
//        view.startAnimation(animAlpha);
//    }

    public void facebookLogin() {

        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        if (object != null) {
                            try {
                                String name = object.getString("name");
                                String email = object.getString("email");
                                String fbUserID = object.getString("id");
                                String imageUrl = "https://graph.facebook.com/" + fbUserID + "/picture?type=normal";
//                                disconnectFromFacebook();
                                Log.d("kurwa facebook", name);
                                Log.d("kurwa facebook", email);
                                Log.d("kurwa facebook", fbUserID);

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            catch (JSONException | NullPointerException e) {
                                e.printStackTrace(); }
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields","id, name, email, gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }
            @Override
            public void onCancel() {
                Log.v("facebookLogin", "---onCancel"); }
            @Override
            public void onError(FacebookException error) {
                Log.v("facebookLogin", "----onError: " + error.getMessage()); }
        });
    }

    public void disconnectFromFacebook() {
        if (AccessToken.getCurrentAccessToken() == null) {
            return;    }
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();  }
        }).executeAsync();
    }

//    logowanie google
//    public void googleClick(View view) {
//        Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.animation_alpha);
//        view.startAnimation(animAlpha);
//    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
        if (acct != null) {
            String person = acct.getDisplayName();
            String name = acct.getGivenName();
            String surname = acct.getFamilyName();
            String email = acct.getEmail();
            String personId = acct.getId();

            Log.d("kurwa google", email);
            Log.d("kurwa google", person);
            Log.d("kurwa google", personId);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        } catch (ApiException e) {
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }
}



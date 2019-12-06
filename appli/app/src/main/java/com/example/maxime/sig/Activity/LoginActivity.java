package com.example.maxime.sig.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxime.sig.Call_API.Api;
import com.example.maxime.sig.Model.AccessToken;
import com.example.maxime.sig.Model.User;
import com.example.maxime.sig.R;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText mailEditText;
    EditText passwordEditText;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextView mailTextView = (TextView) findViewById(R.id.mailTextViewID);
         mailEditText = (EditText) findViewById(R.id.mailEditTextID);
        TextView passwordTextView = (TextView) findViewById(R.id.passwordTextViewID);
         passwordEditText = (EditText) findViewById(R.id.passwordEditTextID);
        Button boutonConnexion = (Button) findViewById(R.id.boutonConnexionID);
        Button boutonVersSignUp = (Button) findViewById(R.id.boutonSignUpID);
        mailTextView.setText("Email ou Nom d'utilisateur");
        passwordTextView.setText("Mot de passe");
        boutonConnexion.setText("Connexion");
        boutonVersSignUp.setText("Enregistrement");

      //  passwordEditText.setHint("Mot de passe");

        intent = new Intent(this, MainActivity.class);
        final Intent intentSignUp = new Intent(this, SignUpActivity.class);

        /*
        *
        * NE PAS EFFACER
        *
        String mail = mailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        intent.putExtra("MAIL",mail);
        intent.putExtra("PASSWORD",password);*/

        boutonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
              //  startActivity(intent);
            }
        });
        boutonVersSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentSignUp);
            }
        });
    }

    private void login(){


        String email = mailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();



        if (email.isEmpty()) {
            mailEditText.setError("Enter a valid login");

            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Enter a valid password");
            return;
        }
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        //Create a new Interceptor.

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("https://psigo.beta9.ovh/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        User user = new User(email,password);
        Call<AccessToken> call = api.login(user);
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                Context context = getApplicationContext();
                CharSequence text;
                int duration = Toast.LENGTH_SHORT;
                if(response.isSuccessful()) {
                    SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
                    preferences.edit().putString("token", response.body().getAccessToken()).commit();
                    String token = getSharedPreferences("myPrefs", MODE_PRIVATE).getAll().get("token").toString();
                    Log.d("ZZZZZZZZZZZZZZ",token);
                    text = "Connection Success";

                    startActivity(intent);
                }
                else {
                    Log.d("XXXXXXXXXXXresponsefail","");
                    text = "Connection Fail response";
                }



                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                CharSequence text="Connection fail";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Log.d("XXXXXXXXXfail",t.getMessage());

            }
        });

    }
}

package com.example.maxime.sig.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxime.sig.Call_API.Api;
import com.example.maxime.sig.Model.User;
import com.example.maxime.sig.R;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignUpActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText usernameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView nameTextView = findViewById(R.id.mailTextViewID);
        TextView usernameTextView = findViewById(R.id.usernameTextViewID);
        TextView emailTextView = findViewById(R.id.emailTextViewID);
        TextView passwordTextView = findViewById(R.id.passwordTextViewID);

          nameEditText = findViewById(R.id.nameEditTextID);
          usernameEditText = findViewById(R.id.usernameEditTextID);
          emailEditText = findViewById(R.id.emailEditTextID);
          passwordEditText = findViewById(R.id.passwordEditTextID);

        Button signUpButton = findViewById(R.id.signUpButton);


        // nameTextView.setText("Nom");
        // usernameTextView.setText("Nom d'utilisateur");
        // emailTextView.setText("Email");
        // passwordTextView.setText("Mot de passe");
        //  signUpButton.setText("OK");
           signUpButton.setOnClickListener(new View.OnClickListener() {
         @Override
           public void onClick(View v) {
             signUp();
         }});

    }
    private void signUp(){
            String name = nameEditText.getText().toString().trim();
            String username = usernameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            Log.d("TTTTTTTTTTTTT",password);


            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.setError("Enter a valid email");

                return;
            }
            if (name.isEmpty()) {
                nameEditText.setError("Enter a valid name");
                return;
            }
            if (username.isEmpty()) {
                usernameEditText.setError("Enter a valid username");
                return;
            }
            if (password.isEmpty()) {
                passwordEditText.setError("Enter a valide password");
                return;
            }
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://psigo.beta9.ovh/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


                Api api = retrofit.create(Api.class);
                User user = new User(name,username,email,password);
                Call<User> call = api.createUser(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Context context = getApplicationContext();
                        CharSequence text;
                        int duration = Toast.LENGTH_SHORT;
                        if(response.isSuccessful()) {
                            text = "Add Success";
                        }
                        else {
                            Log.d("XXXXXXXXXXXresponsefail","");
                            text = "Add Fail";
                        }



                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;
                        CharSequence text="Fail";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Log.d("XXXXXXXXXfail",t.getMessage());
                    }
                });


        }






}

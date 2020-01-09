package com.example.maxime.sig.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxime.sig.R;
import com.example.maxime.sig.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuggestionActivity extends AppCompatActivity {
    EditText editTextSuggest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);



        TextView textViewSuggest = (TextView) findViewById(R.id.suggestTextViewID);
        editTextSuggest = (EditText) findViewById(R.id.suggestEditTextID);


        Button buttonSendSuggest = (Button) findViewById(R.id.buttonSuggestID);

        textViewSuggest.setText("Suggestion");


        buttonSendSuggest.setText("Envoyer Signalement");

        buttonSendSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSuggest();
            }
        });
    }
    private void sendSuggest(){
        if(!editTextSuggest.getText().toString().isEmpty()){

            String comment = editTextSuggest.getText().toString();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://psigo.beta9.ovh/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            Api api = retrofit.create(Api.class);



            SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
            final String token = preferences.getString("token","");

          //  final Intent intent = getIntent();
            String coords="";

            Call call = api.createSuggestion("Bearer "+token,comment,coords);
            call.enqueue(new Callback() {
                Context context = getApplicationContext();
                Toast toast;

                @Override
                public void onResponse(Call call, Response response) {
                    if(response.isSuccessful()){

                        toast = toast.makeText(context,"Ajout réussi",Toast.LENGTH_SHORT);
                        toast.show();
                        gotoNagivationDrawerActivity();
                    }
                    else{
                        toast=toast.makeText(context,"Ajout échec",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    toast=toast.makeText(context,"Ajout erreur",Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }



    }
    private void gotoNagivationDrawerActivity(){
        Intent i = new Intent(this, NavigationDrawerActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //A B C si D appelle A, B,C,D sont delete
        startActivity(i);
    }
}

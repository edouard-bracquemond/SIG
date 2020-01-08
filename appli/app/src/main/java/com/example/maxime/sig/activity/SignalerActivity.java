package com.example.maxime.sig.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maxime.sig.R;

public class SignalerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signaler);



        TextView textViewSignaler = (TextView) findViewById(R.id.signalerTextViewID);
        EditText editTextSignaler = (EditText) findViewById(R.id.signalerEditTextID);
        TextView passwordTextView = (TextView) findViewById(R.id.passwordTextViewID);

        Button boutonEnvoyerSignalement = (Button) findViewById(R.id.boutonSignalerID);

        textViewSignaler.setText("Signalement");


        boutonEnvoyerSignalement.setText("Envoyer Signalement");

        boutonEnvoyerSignalement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //appeler l'api en envoyant le string du text de editTextSignaler
            }
        });
    }


}

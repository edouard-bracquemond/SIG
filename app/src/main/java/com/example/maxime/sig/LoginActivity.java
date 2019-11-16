package com.example.maxime.sig;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextView mailTextView = (TextView) findViewById(R.id.mailTextViewID);
        EditText mailEditText = (EditText) findViewById(R.id.mailEditTextID);
        TextView passwordTextView = (TextView) findViewById(R.id.passwordTextViewID);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditTextID);
        Button boutonConnexion = (Button) findViewById(R.id.boutonConnexionID);

        mailTextView.setText("EMAIL");
        passwordTextView.setText("MOT DE PASSE");
        boutonConnexion.setText("CONNEXION");

        mailEditText.setHint("Email");
        passwordEditText.setHint("Mot de passe");

        final Intent intent = new Intent(this, MainActivity.class);


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
                startActivity(intent);
            }
        });
    }
}

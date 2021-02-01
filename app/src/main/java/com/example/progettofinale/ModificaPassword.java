package com.example.progettofinale;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;

import static com.example.progettofinale.MainActivity.packag;

public class ModificaPassword extends AppCompatActivity {
    TextView nuovapassword, confermaPassword, username, passwordAttuale;
    Utente utente;
    Button aggiornaPwd, home;
    boolean isValid=false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_password);

        aggiornaPwd = findViewById(R.id.aggiorna);
        home = findViewById(R.id.home);
        passwordAttuale = findViewById(R.id.passwordAttuale); //pdw registrazione
        nuovapassword = findViewById(R.id.password);
        confermaPassword = findViewById(R.id.confermaPassword);
        username = findViewById(R.id.username);


/**** Gestione click AGGIORNA PASSWORD una volta inserita la nuova pwd con relativi controlli dei campi vuoti,delle pwd che devono coincidere e
 * che la pwd vecchia sia diversa dalla pwd appena inserita ****/

        aggiornaPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nuovapassword.getText().toString().length() == 0 || confermaPassword.getText().toString().length() == 0
                        || !nuovapassword.getText().toString().equals(confermaPassword.getText().toString())
                        || utente.getPassword().equals(nuovapassword.getText().toString())) {
                    nuovapassword.setError("Errore password");
                    confermaPassword.setError("Errore password");
                } else {
                    String s = nuovapassword.getText().toString();
                    passwordAttuale.setText(s);
                    nuovapassword.setError(null);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "La password è stata aggiornata",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

/**** Gestione click HOME che ci riporta alla home con la password aggiornata ****/

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(isValid){
                utente.setPassword(nuovapassword.getText().toString());
                Intent data = new Intent();
                data.putExtra("Currentuser", utente);
                setResult(RESULT_OK, data);
                finish();
               }
               else {
                   String s = passwordAttuale.getText().toString();
                   utente.setPassword(s);
                   Intent data = new Intent();
                   data.putExtra("Currentuser", utente);
                   setResult(RESULT_OK, data);
                   finish();
               }
            }
        });

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(packag);

        if (obj instanceof Utente) {
            utente = (Utente) obj;
        } else utente = new Utente();

        username.setText(utente.getUsername());
        passwordAttuale.setText(utente.getPassword());
    }
}
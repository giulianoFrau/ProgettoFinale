package com.example.progettofinale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrazione extends AppCompatActivity {
    EditText username, password, passwordConferma, citta, data;
    Button registrazione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordConferma = findViewById(R.id.passwordConferma);
        citta = findViewById(R.id.citta);
        data = findViewById(R.id.data);
        registrazione = findViewById(R.id.registrazione);

/**** Gestione click REGISTRAZIONE UTENTE una volta inseriti i dati ****/

        registrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check()) {
                    try {
                        Utente currentUser = getUtenteFromEditText();
                        Toast t = Toast.makeText(getApplicationContext(), "Utente registrato", Toast.LENGTH_SHORT);
                        t.show();
                        Intent intent = new Intent();
                        intent.putExtra("Currentuser", currentUser);
                        setResult(RESULT_OK, intent);
                        finish();
                    } catch (IllegalArgumentException e) {
                        System.out.println("errore");
                    }
                }
            }
        });
    }

/**** Gestione CONTROLLI GENERALI ****/

/**** Controllo errori campi vuoti && controllo che le due pwd coincidano ****/

    private boolean check() {
        int errors = 0;
        if (username.getText().toString().length() == 0) {
            username.setError("Inserire il nome");
            errors++;
        } else {
            username.setError(null);
        }
        if (password.getText().toString().length() == 0 || passwordConferma.getText().toString().length() == 0
                || !password.getText().toString().equals(passwordConferma.getText().toString())) {
            password.setError("Errore password");
            passwordConferma.setError("Errore password");
            errors++;
        } else {
            password.setError(null);
        }
        if (citta.getText().toString().length() == 0) {
            citta.setError("Inserire una città");
            errors++;
        } else {
            citta.setError(null);
        }
        if (data.getText().toString().length() == 0) {
            data.setError("Inserire una data di nascita");
            errors++;
        } else {
            data.setError(null);
        }
        return errors == 0;
    }

/**** Aggiunta di un utente ****/

    public Utente getUtenteFromEditText() throws IllegalArgumentException {
        Utente utente = new Utente();
        utente.setUsername(username.getText().toString());
        utente.setPassword(password.getText().toString());
        utente.setCitta(citta.getText().toString());
        utente.setDataDiNascita(data.getText().toString());
        return utente;
    }
}
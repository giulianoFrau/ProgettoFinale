package com.example.progettofinale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button accedi;
    TextView registrazione;
    EditText username, password;
    Utente utente;
    ArrayList<Utente> utenti = new ArrayList<Utente>();
    public final static String packag = "com.example.progettofinale.User";
    private final int REQUEST_CODE = 20;
    private final int REQUEST_CODE_HOME = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        accedi = findViewById(R.id.accesso);
        registrazione = findViewById(R.id.registrazione);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        utente = new Utente();

/*** Gestione click ACCESSO UTENTI ****/

        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/**** Gestione utente con credenziali "admin" e gestione arraylist utenti registrati ****/

                String nomeAdmin = "admin";
                String passwordAdmin = "admin";
                if (checkInput()) {
                    if (username.getText().toString().equals(nomeAdmin) && password.getText().toString().equals(passwordAdmin)) {
                        utente.setCitta("cagliari");
                        utente.setDataDiNascita("03/10/1992");
                        updateUtente();
                        Intent showResult = new Intent(MainActivity.this, Home.class);
                        showResult.putExtra(packag, utente);
                        startActivity(showResult);
                        clearLogIn();
                    } else if (checkInput()) {
                        for (Utente u : utenti) {
                            if (username.getText().toString().equals(u.getUsername()) && password.getText().toString().equals(u.getPassword())) {
                                Intent showResult = new Intent(MainActivity.this, Home.class);
                                showResult.putExtra(packag, u);
                                startActivityForResult(showResult, REQUEST_CODE_HOME);
                                clearLogIn();
                            }
                        }
                    } else {
                        Toast t = Toast.makeText(getApplicationContext(), "Utente non registrato o non esistente", Toast.LENGTH_SHORT);
                        t.show();
                    }
                }
            }
        });

/**** Gestione click REGISTRAZIONE UTENTE ****/

        registrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResult2 = new Intent(MainActivity.this, Registrazione.class);
                startActivityForResult(showResult2, REQUEST_CODE);
            }
        });
    }

/**** Gestione CONTROLLI GENERALI****/

/**** Aggiunta utente all'arraylist e controllo aggiornamento password di un utente prendendo i dati dall'activity registrazione e modifica password ****/

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            Utente name = (Utente) data.getExtras().get("Currentuser");
            utenti.add(name);
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_HOME) {
            Utente name = (Utente) data.getExtras().get("Currentuser");
            for (Utente ut : utenti) {
                if (ut.getUsername().equals(name.getUsername())) {
                    ut.setPassword(name.getPassword());
                }
            }
        }
    }

/*** Controllo errori campi vuoti username & password ****/

    private boolean checkInput() {
        int errors = 0;
        if (username.getText().toString().length() == 0) {
            username.setError("Inserire il nome");
            errors++;
        } else {
            username.setError(null);
        }
        if (password.getText().toString().length() == 0) {
            password.setError("Inserire la password");
            errors++;
        } else {
            password.setError(null);
        }
        return errors == 0;
    }

/*** Log in utente tramite username & password ****/

    public void updateUtente() {
        this.utente.setUsername(username.getText().toString());
        this.utente.setPassword(password.getText().toString());
    }

/*** Pulizia campi username & password una volta fatto l'accesso ****/

    private void clearLogIn() {
        username.setText("");
        password.setText("");
    }
}
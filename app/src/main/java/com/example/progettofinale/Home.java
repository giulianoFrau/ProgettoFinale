package com.example.progettofinale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.Serializable;

import static com.example.progettofinale.MainActivity.packag;

public class Home extends AppCompatActivity {
    TextView username, password, citta, data, benvenuto, cambiaPwd;
    Utente utente;
    Button logOut;
    private final int REQUEST_CODE = 20;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        benvenuto = findViewById(R.id.benvenuto);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        citta = findViewById(R.id.citta);
        data = findViewById(R.id.data);
        logOut = findViewById(R.id.logout);
        cambiaPwd = findViewById(R.id.cambiopassword);

/*** Gestione Log out che ci riporta alla pagina di Log in ****/

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Currentuser", utente);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

/*** Gestione CambiaPassword  che ci porta alla pagina di moedifica della stessa ****/

        cambiaPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResults = new Intent(Home.this, ModificaPassword.class);
                showResults.putExtra(packag, utente);
                startActivityForResult(showResults, REQUEST_CODE);
            }
        });

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(packag);

        if (obj instanceof Utente) {
            utente = (Utente) obj;
        } else utente = new Utente();

        benvenuto.setText("Benvenuto " + utente.getUsername());
        username.setText("Utente: " + utente.getUsername());
        password.setText("Password: "+utente.getPassword());
        citta.setText("Citta:  " + utente.getCitta());
        data.setText("Data:  " + utente.getDataDiNascita());
    }

/**** Controllo per riportarci alla pagina di Log in con la nuova password  ****/

    @SuppressLint("SetTextI18n")
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            Utente updateUser = (Utente) data.getExtras().get("Currentuser");
            password.setText("Password: "+updateUser.getPassword());
            utente = updateUser;

        }
    }

}
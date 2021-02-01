package com.example.progettofinale;

import java.io.Serializable;


public class Utente implements Serializable {
    private String username;
    private String password;
    private String citta;
    private String dataDiNascita;

    public Utente(String username, String password, String citta, String dataDiNascita) {
        this.username = username;
        this.password = password;
        this.citta = citta;
        this.dataDiNascita = dataDiNascita;
    }

    public Utente() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

}

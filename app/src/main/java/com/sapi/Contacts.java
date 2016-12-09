package com.sapi;

/**
 * Created by nyulg on 2016. 12. 09..
 */

public class Contacts {
    private String nev;
    private String pozicio;
    private String telefonszam;
    private String email;
    private String szoba;
    private String felfogadas;
    private int TanulmanyiOsztaly;

    public String getNev() {
        return nev;
    }

    public String getPozicio() {
        return pozicio;
    }

    public String getNumber() {
        return telefonszam;
    }

    public String getMail() {
        return email;
    }

    public String getSzoba() {return szoba;}

    public int getTO() {return TanulmanyiOsztaly;}

    public String getFelfogadas() {return felfogadas;}

}

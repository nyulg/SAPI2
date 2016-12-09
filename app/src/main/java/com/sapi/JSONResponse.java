package com.sapi;

import java.util.List;

/**
 * Created by nyulg on 2016. 11. 30..
 */

public class JSONResponse {
    private Market[] jegyzetek;

    public Market[] getMarket() {
        return jegyzetek;
    }

    private Market[] alberlet;

    public Market[] getRent() {return alberlet;}

    private Contacts[] telefonkonyv;

    public Contacts [] getContacts(){return telefonkonyv;}

    private Events [] naptar;

    public Events [] getEvents(){return naptar;}
}

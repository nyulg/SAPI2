package com.sapi;

/**
 * Created by nyulg on 2016. 11. 12..
 */

public class ServerResponse {
    private String result;
    private String message;
    private User user;
    private AddMarket addMarket;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public AddMarket getAddMarket(){return addMarket;}
}

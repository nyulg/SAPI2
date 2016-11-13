package com.sapi;

/**
 * Created by nyulg on 2016. 11. 12..
 */

public class ServerRequest {
    private String operation;
    private User user;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

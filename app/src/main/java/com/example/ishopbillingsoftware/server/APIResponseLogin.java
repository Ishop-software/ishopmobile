package com.example.ishopbillingsoftware.server;

public class APIResponseLogin {
    private String success;
    private String message;

    private String token;
    private String activationkey;



    public APIResponseLogin(String success, String message, String activationkey, String token) {
        this.success = success;
        this.message = message;
        this.activationkey = activationkey;
        this.token = token;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getActivationkey() {
        return activationkey;
    }

    public void setActivationkey(String activationkey) {
        this.activationkey = activationkey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

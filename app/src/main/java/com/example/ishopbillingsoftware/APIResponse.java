package com.example.ishopbillingsoftware;



public class APIResponse {
    private String success;
    private String message;

    private String userId;

    private String isFirstLogin;

    public APIResponse(String success, String message, String userId, String isFirstLogin) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.isFirstLogin = isFirstLogin;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsFirstLogin() {
        return isFirstLogin;
    }

    public void setIsFirstLogin(String isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }
}


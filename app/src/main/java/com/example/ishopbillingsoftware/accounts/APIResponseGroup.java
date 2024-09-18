package com.example.ishopbillingsoftware.accounts;

public class APIResponseGroup {
    private String success;
    private String message;
    private  String groupAccountId;


    public APIResponseGroup(String success, String message, String groupAccountId) {
        this.success = success;
        this.message = message;
        this.groupAccountId = groupAccountId;
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

    public String getGroupAccountId() {
        return groupAccountId;
    }

    public void setGroupAccountId(String groupAccountId) {
        this.groupAccountId = groupAccountId;
    }
}

package com.example.ishopbillingsoftware.accounts;

import java.util.List;

public class APIResponseGP {
    private String success;
    private List<AccountGroupList> message;


    public APIResponseGP(String success, List<AccountGroupList> message) {
        this.success = success;
        this.message = message;
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<AccountGroupList> getMessage() {
        return message;
    }

    public void setMessage(List<AccountGroupList> message) {
        this.message = message;
    }
}

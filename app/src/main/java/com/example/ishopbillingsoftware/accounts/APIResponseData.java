package com.example.ishopbillingsoftware.accounts;

import java.util.List;

public class APIResponseData {
    private String success;

    private List<AccountList> data;


    public APIResponseData(String success, List<AccountList> data) {
        this.success = success;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<AccountList> getData() {
        return data;
    }

    public void setData(List<AccountList> data) {
        this.data = data;
    }
}

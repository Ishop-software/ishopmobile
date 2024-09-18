package com.example.ishopbillingsoftware.accounts;

import java.util.List;

public class APIResponseData {
    private String success;

    private List<Data> data;


    public APIResponseData(String success, List<Data> data) {
        this.success = success;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}

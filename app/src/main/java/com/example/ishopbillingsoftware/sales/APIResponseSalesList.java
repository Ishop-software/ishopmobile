package com.example.ishopbillingsoftware.sales;

import java.util.List;

public class APIResponseSalesList {
    private String success;
    private List<Salescharges> message;

    public APIResponseSalesList(String success, List<Salescharges> message) {
        this.success = success;
        this.message = message;
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Salescharges> getMessage() {
        return message;
    }

    public void setMessage(List<Salescharges> message) {
        this.message = message;
    }



}

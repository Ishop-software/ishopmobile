package com.example.ishopbillingsoftware.sales;

import java.util.List;

public class APIResponseChargesList {
    private String success;
    private List<ChargesList> message;


    public APIResponseChargesList(String success, List<ChargesList> message) {
        this.success = success;
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ChargesList> getMessage() {
        return message;
    }

    public void setMessage(List<ChargesList> message) {
        this.message = message;
    }
}

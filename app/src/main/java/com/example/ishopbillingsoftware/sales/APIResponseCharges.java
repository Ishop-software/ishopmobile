package com.example.ishopbillingsoftware.sales;

public class APIResponseCharges {
    private  String success;
    private String message;
    private  String chargeRegId;

    public APIResponseCharges(String success, String message, String chargeRegId) {
        this.success = success;
        this.message = message;
        this.chargeRegId = chargeRegId;
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

    public String getChargeRegId() {
        return chargeRegId;
    }

    public void setChargeRegId(String chargeRegId) {
        this.chargeRegId = chargeRegId;
    }
}

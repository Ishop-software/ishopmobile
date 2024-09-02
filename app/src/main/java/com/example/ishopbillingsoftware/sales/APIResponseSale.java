package com.example.ishopbillingsoftware.sales;

public class APIResponseSale {

    private String success;
    private String message;

    private String saleRegId;


    public APIResponseSale(String success, String message, String saleRegId) {
        this.success = success;
        this.message = message;
        this.saleRegId = saleRegId;
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

    public String getSaleRegId() {
        return saleRegId;
    }

    public void setSaleRegId(String saleRegId) {
        this.saleRegId = saleRegId;
    }
}

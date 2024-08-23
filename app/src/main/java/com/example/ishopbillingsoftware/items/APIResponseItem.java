package com.example.ishopbillingsoftware.items;

public class APIResponseItem {

    private String success;
    private String message;

    private String productItemId;


    public APIResponseItem(String success, String message, String productItemId) {
        this.success = success;
        this.message = message;
        this.productItemId = productItemId;
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

    public String getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(String productItemId) {
        this.productItemId = productItemId;
    }
}

package com.example.ishopbillingsoftware.items;

import java.util.List;

public class APIResponseProductItem {

    private String success;
    private List<ProductItem> message;

    public APIResponseProductItem(String success, List<ProductItem> message) {

        this.success = success;
        this.message = message;
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ProductItem> getMessage() {
        return message;
    }

    public void setMessage(List<ProductItem> message) {
        this.message = message;
    }
}

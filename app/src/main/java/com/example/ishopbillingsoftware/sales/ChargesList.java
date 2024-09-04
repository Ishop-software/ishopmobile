package com.example.ishopbillingsoftware.sales;

import java.util.List;

public class ChargesList {

    private String _id;
    private String chargeRegId;
    private String chargesHeading;
    private String accountHeadToPost;
    private String typesOfCharges;
    private String inputAmountOfChargesAs;
    private List<TaxSetting> taxSettings;
    private int __v;

    public ChargesList(String _id, String chargeRegId, String chargesHeading, String accountHeadToPost, String typesOfCharges, String inputAmountOfChargesAs, List<TaxSetting> taxSettings, int __v) {
        this._id = _id;
        this.chargeRegId = chargeRegId;
        this.chargesHeading = chargesHeading;
        this.accountHeadToPost = accountHeadToPost;
        this.typesOfCharges = typesOfCharges;
        this.inputAmountOfChargesAs = inputAmountOfChargesAs;
        this.taxSettings = taxSettings;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getChargeRegId() {
        return chargeRegId;
    }

    public void setChargeRegId(String chargeRegId) {
        this.chargeRegId = chargeRegId;
    }

    public String getChargesHeading() {
        return chargesHeading;
    }

    public void setChargesHeading(String chargesHeading) {
        this.chargesHeading = chargesHeading;
    }

    public String getAccountHeadToPost() {
        return accountHeadToPost;
    }

    public void setAccountHeadToPost(String accountHeadToPost) {
        this.accountHeadToPost = accountHeadToPost;
    }

    public String getTypesOfCharges() {
        return typesOfCharges;
    }

    public void setTypesOfCharges(String typesOfCharges) {
        this.typesOfCharges = typesOfCharges;
    }

    public String getInputAmountOfChargesAs() {
        return inputAmountOfChargesAs;
    }

    public void setInputAmountOfChargesAs(String inputAmountOfChargesAs) {
        this.inputAmountOfChargesAs = inputAmountOfChargesAs;
    }

    public List<TaxSetting> getTaxSettings() {
        return taxSettings;
    }

    public void setTaxSettings(List<TaxSetting> taxSettings) {
        this.taxSettings = taxSettings;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}

package com.example.ishopbillingsoftware.accounts;

import com.google.gson.annotations.SerializedName;

public class AccountList {
    private String accountId;
    private String name;
    private String printAs;
    private String group;
    private String openingBal;
    private String taxNo;

    @SerializedName("Address1")
    private String address1;
    private String city;
    private String pincode;
    private String state;
    private String stateCode;
    private String mobileNo;
    private String email;
    private String contactPerson;
    private String createdAt;
    private  String updateAt;


    public AccountList(String accountId, String name, String printAs, String group, String openingBal, String taxNo, String address1, String city, String pincode, String state, String stateCode, String mobileNo, String email, String contactPerson, String createdAt, String updateAt) {
        this.accountId = accountId;
        this.name = name;
        this.printAs = printAs;
        this.group = group;
        this.openingBal = openingBal;
        this.taxNo = taxNo;
        this.address1 = address1;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.stateCode = stateCode;
        this.mobileNo = mobileNo;
        this.email = email;
        this.contactPerson = contactPerson;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrintAs() {
        return printAs;
    }

    public void setPrintAs(String printAs) {
        this.printAs = printAs;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getOpeningBal() {
        return openingBal;
    }

    public void setOpeningBal(String openingBal) {
        this.openingBal = openingBal;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}

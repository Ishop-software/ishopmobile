package com.example.ishopbillingsoftware.accounts;

public class Data {

    private String userId;
    private String accountId;
    private String name;
    private String printAs;
    private String group;
    private double openingBal;
    private String DR_CR;
    private String taxNo;
    private String Address1;
    private String city;
    private int pincode;
    private String state;
    private String stateCode;
    private String mobileNo;
    private String email;
    private String contactPerson;


    public Data(String userId, String accountId, String name, String printAs, String group, double openingBal, String DR_CR, String taxNo, String address1, String city, int pincode, String state, String stateCode, String mobileNo, String email, String contactPerson) {
        this.userId = userId;
        this.accountId = accountId;
        this.name = name;
        this.printAs = printAs;
        this.group = group;
        this.openingBal = openingBal;
        this.DR_CR = DR_CR;
        this.taxNo = taxNo;
        Address1 = address1;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.stateCode = stateCode;
        this.mobileNo = mobileNo;
        this.email = email;
        this.contactPerson = contactPerson;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public double getOpeningBal() {
        return openingBal;
    }

    public void setOpeningBal(double openingBal) {
        this.openingBal = openingBal;
    }

    public String getDR_CR() {
        return DR_CR;
    }

    public void setDR_CR(String DR_CR) {
        this.DR_CR = DR_CR;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
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
}

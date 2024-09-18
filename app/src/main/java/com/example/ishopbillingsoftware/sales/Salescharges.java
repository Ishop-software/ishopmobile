package com.example.ishopbillingsoftware.sales;

public class Salescharges {
    private String userId;
    private String saleRegId;
    private String partyName;
    private int billNo;
    private String dueDate;
    private String itemName;
    private int qty;
    private int altQty;
    private int free;
    private String per;
    private double rate;
    private double discAmount;
    private double taxAmount;
    private double discs;
    private double netValue;
    private String otherCharges;
    private String remarks;
    private double onValue;
    private String at;
    private String plusMinus;
    private double amount;


    public Salescharges(String userId, String saleRegId, String partyName, int billNo, String dueDate, String itemName, int qty, int altQty, int free, String per, double rate, double discAmount, double taxAmount, double discs, double netValue, String otherCharges, String remarks, double onValue, String at, String plusMinus, double amount) {
        this.userId = userId;
        this.saleRegId = saleRegId;
        this.partyName = partyName;
        this.billNo = billNo;
        this.dueDate = dueDate;
        this.itemName = itemName;
        this.qty = qty;
        this.altQty = altQty;
        this.free = free;
        this.per = per;
        this.rate = rate;
        this.discAmount = discAmount;
        this.taxAmount = taxAmount;
        this.discs = discs;
        this.netValue = netValue;
        this.otherCharges = otherCharges;
        this.remarks = remarks;
        this.onValue = onValue;
        this.at = at;
        this.plusMinus = plusMinus;
        this.amount = amount;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSaleRegId() {
        return saleRegId;
    }

    public void setSaleRegId(String saleRegId) {
        this.saleRegId = saleRegId;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public int getBillNo() {
        return billNo;
    }

    public void setBillNo(int billNo) {
        this.billNo = billNo;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getAltQty() {
        return altQty;
    }

    public void setAltQty(int altQty) {
        this.altQty = altQty;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getDiscAmount() {
        return discAmount;
    }

    public void setDiscAmount(double discAmount) {
        this.discAmount = discAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getDiscs() {
        return discs;
    }

    public void setDiscs(double discs) {
        this.discs = discs;
    }

    public double getNetValue() {
        return netValue;
    }

    public void setNetValue(double netValue) {
        this.netValue = netValue;
    }

    public String getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(String otherCharges) {
        this.otherCharges = otherCharges;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getOnValue() {
        return onValue;
    }

    public void setOnValue(double onValue) {
        this.onValue = onValue;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(String plusMinus) {
        this.plusMinus = plusMinus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

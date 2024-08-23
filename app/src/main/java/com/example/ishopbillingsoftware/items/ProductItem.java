package com.example.ishopbillingsoftware.items;

import java.util.Date;

public class ProductItem {
    private String _id;
    private String productItemId;
    private String itemName;
    private String shortName;
    private String HSNCode;
    private int taxSlab;
    private String primaryUnit;
    private String company;
    private String uploadImage;
    private boolean maintainBatch;
    private String group;
    private boolean seriolNoTracking;
    private String variation;
    private String color;
    private String size;
    private Date expDate;
    private Date mfgDate;
    private double purchase;
    private double salePrice;
    private double mrp;
    private double basicPrice;
    private double selfVal;
    private double minSalePrice;
    private String barcode;
    private int openingPck;
    private double openingValue;
    private boolean delete;
    private boolean copy;
    private String details;
    private int __v;


    public ProductItem(String _id, String productItemId, String itemName, String shortName, String HSNCode, int taxSlab, String primaryUnit, String company, String uploadImage, boolean maintainBatch, String group, boolean seriolNoTracking, String variation, String color, String size, Date expDate, Date mfgDate, double purchase, double salePrice, double mrp, double basicPrice, double selfVal, double minSalePrice, String barcode, int openingPck, double openingValue, boolean delete, boolean copy, String details, int __v) {
        this._id = _id;
        this.productItemId = productItemId;
        this.itemName = itemName;
        this.shortName = shortName;
        this.HSNCode = HSNCode;
        this.taxSlab = taxSlab;
        this.primaryUnit = primaryUnit;
        this.company = company;
        this.uploadImage = uploadImage;
        this.maintainBatch = maintainBatch;
        this.group = group;
        this.seriolNoTracking = seriolNoTracking;
        this.variation = variation;
        this.color = color;
        this.size = size;
        this.expDate = expDate;
        this.mfgDate = mfgDate;
        this.purchase = purchase;
        this.salePrice = salePrice;
        this.mrp = mrp;
        this.basicPrice = basicPrice;
        this.selfVal = selfVal;
        this.minSalePrice = minSalePrice;
        this.barcode = barcode;
        this.openingPck = openingPck;
        this.openingValue = openingValue;
        this.delete = delete;
        this.copy = copy;
        this.details = details;
        this.__v = __v;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(String productItemId) {
        this.productItemId = productItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getHSNCode() {
        return HSNCode;
    }

    public void setHSNCode(String HSNCode) {
        this.HSNCode = HSNCode;
    }

    public int getTaxSlab() {
        return taxSlab;
    }

    public void setTaxSlab(int taxSlab) {
        this.taxSlab = taxSlab;
    }

    public String getPrimaryUnit() {
        return primaryUnit;
    }

    public void setPrimaryUnit(String primaryUnit) {
        this.primaryUnit = primaryUnit;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(String uploadImage) {
        this.uploadImage = uploadImage;
    }

    public boolean isMaintainBatch() {
        return maintainBatch;
    }

    public void setMaintainBatch(boolean maintainBatch) {
        this.maintainBatch = maintainBatch;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean isSeriolNoTracking() {
        return seriolNoTracking;
    }

    public void setSeriolNoTracking(boolean seriolNoTracking) {
        this.seriolNoTracking = seriolNoTracking;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(Date mfgDate) {
        this.mfgDate = mfgDate;
    }

    public double getPurchase() {
        return purchase;
    }

    public void setPurchase(double purchase) {
        this.purchase = purchase;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public double getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(double basicPrice) {
        this.basicPrice = basicPrice;
    }

    public double getSelfVal() {
        return selfVal;
    }

    public void setSelfVal(double selfVal) {
        this.selfVal = selfVal;
    }

    public double getMinSalePrice() {
        return minSalePrice;
    }

    public void setMinSalePrice(double minSalePrice) {
        this.minSalePrice = minSalePrice;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getOpeningPck() {
        return openingPck;
    }

    public void setOpeningPck(int openingPck) {
        this.openingPck = openingPck;
    }

    public double getOpeningValue() {
        return openingValue;
    }

    public void setOpeningValue(double openingValue) {
        this.openingValue = openingValue;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isCopy() {
        return copy;
    }

    public void setCopy(boolean copy) {
        this.copy = copy;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}

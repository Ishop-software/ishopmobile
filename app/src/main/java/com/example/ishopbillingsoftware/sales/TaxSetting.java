package com.example.ishopbillingsoftware.sales;

public class TaxSetting {
    private String taxSlab;
    private String HSNCode;
    private String taxApplicable;

    public TaxSetting(String taxSlab, String HSNCode, String taxApplicable) {
        this.taxSlab = taxSlab;
        this.HSNCode = HSNCode;
        this.taxApplicable = taxApplicable;
    }


    public String getTaxSlab() {
        return taxSlab;
    }

    public void setTaxSlab(String taxSlab) {
        this.taxSlab = taxSlab;
    }

    public String getHSNCode() {
        return HSNCode;
    }

    public void setHSNCode(String HSNCode) {
        this.HSNCode = HSNCode;
    }

    public String isTaxApplicable() {
        return taxApplicable;
    }

    public void setTaxApplicable(String taxApplicable) {
        this.taxApplicable = taxApplicable;
    }
}

package com.citronix.dto.req;

import java.util.Date;

public class SaleDTO {
    private Date date;
    private double pricePerUnit;
    private double quantitySold;
    private Long harvestId; 

    public SaleDTO() {
    }

    public SaleDTO(Date date, double pricePerUnit, double quantitySold, Long harvestId) {
        this.date = date;
        this.pricePerUnit = pricePerUnit;
        this.quantitySold = quantitySold;
        this.harvestId = harvestId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(double quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Long getHarvestId() {
        return harvestId;
    }

    public void setHarvestId(Long harvestId) {
        this.harvestId = harvestId;
    }
}

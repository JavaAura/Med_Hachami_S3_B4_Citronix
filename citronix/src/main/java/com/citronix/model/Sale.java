package com.citronix.model;

import java.util.Date;

import com.citronix.model.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Represents a Sale entity in the application.
 */
@Table(name = "sales")
@Entity
public class Sale extends BaseEntity {
    @Temporal(TemporalType.DATE)
    private Date date;

    private double pricePerUnit;
    private double quantitySold;
    
    @ManyToOne
    @JoinColumn(name = "harvest_id", referencedColumnName = "id")
    private Harvest harvest; 

    private double revenue; 

    public Sale() {

    }

    public Sale(Date date, double pricePerUnit, double quantitySold, Harvest harvest) {
        this.date = date;
        this.pricePerUnit = pricePerUnit;
        this.quantitySold = quantitySold;
        this.harvest = harvest;
    }

    public void calculateRevenue() {
        this.revenue =  this.quantitySold * this.pricePerUnit;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
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

    public Harvest getHarvest() {
        return harvest;
    }

    public void setHarvest(Harvest harvest) {
        this.harvest = harvest;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }


}

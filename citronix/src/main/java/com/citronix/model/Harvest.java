package com.citronix.model;

import java.time.LocalDate;
import java.util.List;

import com.citronix.model.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a Harvest entity in the application.
 */
@Table(name = "harvests")
@Entity
public class Harvest extends BaseEntity {



    @NotNull
    private String season;

    @NotNull
    private LocalDate harvestDate; 

    @NotNull
    private Integer totalQuantity; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    private Field field; 

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HarvestDetail> harvestDetails; // List of harvest details for each tree

    // Constructors
    public Harvest() {}

    public Harvest(String season, LocalDate harvestDate, Integer totalQuantity, Field field) {
        this.season = season;
        this.harvestDate = harvestDate;
        this.totalQuantity = totalQuantity;
        this.field = field;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(LocalDate harvestDate) {
        this.harvestDate = harvestDate;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public List<HarvestDetail> getHarvestDetails() {
        return harvestDetails;
    }

    public void setHarvestDetails(List<HarvestDetail> harvestDetails) {
        this.harvestDetails = harvestDetails;
    }

}

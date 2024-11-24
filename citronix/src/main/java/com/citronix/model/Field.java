package com.citronix.model;

import java.util.List;

import com.citronix.model.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a Field entity in the application.
 */
@Table(name = "fields")
@Entity
public class Field extends BaseEntity {

    private String fieldName;
    private Double fieldArea;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Tree> trees;

    // @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    // private List<Harvest> harvests;

  public Long getId() {
      return super.getId();
  }

    public void setId(Long id) {
        super.setId(id);
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Double getFieldArea() {
        return fieldArea;
    }

    public void setFieldArea(Double fieldArea) {
        this.fieldArea = fieldArea;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public List<Tree> getTrees() {
    return trees;
    }

    public void setTrees(List<Tree> trees) {
    this.trees = trees;
    }

    // public List<Harvest> getHarvests() {
    // return harvests;
    // }

    // public void setHarvests(List<Harvest> harvests) {
    // this.harvests = harvests;
    // }

}

package com.citronix.model;

import jakarta.persistence.*;


import com.citronix.model.entity.BaseEntity;
import java.util.*;

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

  /*  @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Tree> trees;
*/

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
}

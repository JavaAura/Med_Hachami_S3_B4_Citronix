package com.citronix.dto.res;

import com.citronix.dto.req.FarmDTO;

public class FieldDisplayDTO {
    private Long id;
    private String name;
    private Double area;
    private FarmDTO farm;  // FarmDTO for showing farm details



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public FarmDTO getFarm() {
        return farm;
    }

    public void setFarm(FarmDTO farm) {
        this.farm = farm;
    }
}

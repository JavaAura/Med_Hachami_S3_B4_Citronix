package com.citronix.dto.req;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FieldDTO {
    private long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;


    @DecimalMin(value = "0.1", message = "Area must be greater than 0")
    @NotNull(message = "Area is required")
    private Double area;

    @NotNull(message = "Farm is  is required")
    private Long farmId;

    public FieldDTO(){}

    public FieldDTO(long id, String name, Double area, Long farmId) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.farmId = farmId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }
}

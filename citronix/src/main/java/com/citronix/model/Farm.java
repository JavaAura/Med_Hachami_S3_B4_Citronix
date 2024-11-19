package com.citronix.model;
import com.citronix.model.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Farm extends BaseEntity {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String farmName;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 200, message = "Address must not exceed 200 characters")
    private String farmAddress;

    @Min(value = 0, message = "Surface must be greater than 0")
    @NotNull(message = "Surface is required")
    private Double farmSurface;

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmAddress() {
        return farmAddress;
    }

    public void setFarmAddress(String farmAddress) {
        this.farmAddress = farmAddress;
    }

    public Double getFarmSurface() {
        return farmSurface;
    }

    public void setFarmSurface(Double farmSurface) {
        this.farmSurface = farmSurface;
    }
}

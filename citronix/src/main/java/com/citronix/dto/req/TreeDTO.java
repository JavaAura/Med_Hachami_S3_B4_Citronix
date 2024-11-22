package com.citronix.dto.req;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class TreeDTO {

     @NotNull(message = "Field ID cannot be null")
    private Long fieldId;

    @NotNull(message = "Planted date cannot be null")
    @PastOrPresent(message = "Planted date must be in the past or today")
    private LocalDate plantedAt;


    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public LocalDate getPlantedAt() {
        return plantedAt;
    }

    public void setPlantedAt(LocalDate plantedAt) {
        this.plantedAt = plantedAt;
    }


    public TreeDTO() {
    }

    public TreeDTO(Long fieldId, LocalDate plantedAt) {
        this.fieldId = fieldId;
        this.plantedAt = plantedAt;
    }}

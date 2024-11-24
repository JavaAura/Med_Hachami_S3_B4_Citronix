package com.citronix.dto.req;

import java.time.LocalDate;

import com.citronix.model.enums.Level;

import jakarta.validation.constraints.NotNull;

public class TreeUpdateDTO {
    @NotNull(message = "Planted date cannot be null")
    private LocalDate plantedAt;

    @NotNull(message = "Level cannot be null")
    private Level level;

    @NotNull(message = "Field ID cannot be null")
    private Long fieldId;

    public LocalDate getPlantedAt() {
        return plantedAt;
    }

    public void setPlantedAt(LocalDate plantedAt) {
        this.plantedAt = plantedAt;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }
}

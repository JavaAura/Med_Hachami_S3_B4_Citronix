package com.citronix.dto.res;

import java.time.LocalDate;

public class TreeResponseDTO {
    private Long id;
    private LocalDate plantedAt;
    private String level;
    private Long fieldId;
    private String fieldName;

    public TreeResponseDTO() {
    }

    public TreeResponseDTO(Long id, LocalDate plantedAt, String level, Long fieldId, String fieldName) {
        this.id = id;
        this.plantedAt = plantedAt;
        this.level = level;
        this.fieldId = fieldId;
        this.fieldName = fieldName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPlantedAt() {
        return plantedAt;
    }

    public void setPlantedAt(LocalDate plantedAt) {
        this.plantedAt = plantedAt;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}

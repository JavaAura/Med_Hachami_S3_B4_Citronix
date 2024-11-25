package com.citronix.dto.req;

import java.time.LocalDate;
import java.util.List;


public class HarvestDTO {
    private long id;
    private LocalDate harvestDate; 
    private String season;
    private Long fieldId; 
    private List<HarvestDetailDTO> harvestDetails; 


    public LocalDate getHarvestDate(){
        return this.harvestDate;
    }

    public void setHarvestDate(LocalDate harvestDate){
        this.harvestDate = harvestDate;
    }

    public String getSeason(){
        return this.season;
    }

    public void setSeason(String season){
        this.season = season;
    }

    public Long getFieldId(){
        return this.fieldId;
    }

    public void setFieldId(Long fieldId){
        this.fieldId = fieldId;
    }

    public List<HarvestDetailDTO> getHarvestDetails() {
        return harvestDetails;
    }

    public void setHarvestDetails(List<HarvestDetailDTO> harvestDetails) {
        this.harvestDetails = harvestDetails;
    }






}

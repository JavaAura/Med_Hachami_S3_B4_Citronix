package com.citronix.dto.req;


public class HarvestDetailDTO {
    // private long id;
    private Long treeId; 
    private Integer quantity; 


    public Long getTreeId() {
        return treeId;
    }

    // Setter for treeId
    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    // Getter for quantity
    public Integer getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

  
}

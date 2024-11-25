package com.citronix.model;
import com.citronix.model.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a HarvestDetail entity in the application.
 */
@Table(name = "harvest_tree")
@Entity
public class HarvestDetail extends BaseEntity {

  
    @NotNull
    private Integer quantity; // Quantity harvested from a specific tree

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tree_id",referencedColumnName = "id")
    private Tree tree; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;

    // Constructors
    public HarvestDetail() {}

    public HarvestDetail(Integer quantity, Tree tree, Harvest harvest) {
        this.quantity = quantity;
        this.tree = tree;
        this.harvest = harvest;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public Long getTreeId() {
        return tree != null ? tree.getId() : null;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public Harvest getHarvest() {
        return harvest;
    }

    public void setHarvest(Harvest harvest) {
        this.harvest = harvest;
    }

}

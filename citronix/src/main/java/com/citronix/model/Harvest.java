package com.citronix.model;

import jakarta.persistence.*;


import com.citronix.model.entity.BaseEntity;

/**
 * Represents a Harvest entity in the application.
 */
@Table(name = "harvests")
@Entity
public class Harvest extends BaseEntity {

}

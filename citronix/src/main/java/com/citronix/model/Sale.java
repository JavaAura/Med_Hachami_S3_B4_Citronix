package com.citronix.model;

import jakarta.persistence.*;

import com.citronix.model.entity.BaseEntity;

/**
 * Represents a Sale entity in the application.
 */
@Table(name = "sales")
@Entity
public class Sale extends BaseEntity {

}

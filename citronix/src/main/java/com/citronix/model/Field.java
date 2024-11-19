package com.citronix.model;

import jakarta.persistence.*;


import com.citronix.model.entity.BaseEntity;

/**
 * Represents a Field entity in the application.
 */
@Table(name = "fields")
@Entity
public class Field extends BaseEntity {
    @Id
    private Long id;
}

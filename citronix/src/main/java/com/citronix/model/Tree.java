package com.citronix.model;

import jakarta.persistence.*;

import com.citronix.model.entity.BaseEntity;

/**
 * Represents a Tree entity in the application.
 */
@Table(name = "trees")
@Entity
public class Tree extends BaseEntity {

}

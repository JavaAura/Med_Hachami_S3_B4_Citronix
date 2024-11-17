package com.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citronix.model.Field;

/**
 * Repository interface for Field entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface FieldRepository extends JpaRepository<Field, Long>{

}

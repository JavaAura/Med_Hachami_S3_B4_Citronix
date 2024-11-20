package com.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citronix.model.Field;

/**
 * Repository interface for Field entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface FieldRepository extends JpaRepository<Field, Long>{

    @Query("SELECT f FROM Field f JOIN FETCH f.farm WHERE f.id = :id")
    Field findByIdWithFarm(@Param("id") Long id);
}

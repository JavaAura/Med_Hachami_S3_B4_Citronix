package com.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citronix.model.Field;

import java.util.List;

/**
 * Repository interface for Field entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface FieldRepository extends JpaRepository<Field, Long>{


    @Query("SELECT f FROM Field f JOIN FETCH f.farm WHERE f.farm.id = :farmId")
    List<Field> findByFarmId(@Param("farmId") Long farmId);

    @Query("SELECT SUM(f.fieldArea) FROM Field f WHERE f.farm.id = :farmId")
    Double sumFieldAreasByFarmId(@Param("farmId") Long farmId);

}

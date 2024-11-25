package com.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citronix.model.Harvest;

/**
 * Repository interface for Harvest entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long>{
    boolean existsByFieldIdAndSeason(Long fieldId, String season);
}

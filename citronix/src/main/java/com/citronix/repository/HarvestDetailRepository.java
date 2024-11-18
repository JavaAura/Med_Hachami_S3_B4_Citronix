package com.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citronix.model.HarvestDetail;

/**
 * Repository interface for HarvestDetail entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, Long>{

}

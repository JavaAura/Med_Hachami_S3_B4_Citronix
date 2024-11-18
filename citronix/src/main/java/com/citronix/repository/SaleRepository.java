package com.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citronix.model.Sale;

/**
 * Repository interface for Sale entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{

}

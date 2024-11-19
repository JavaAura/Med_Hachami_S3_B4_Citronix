package com.citronix.repository;

import com.citronix.dto.req.FarmDTO;
import com.citronix.mapper.FarmMapperDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citronix.model.Farm;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository interface for Farm entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long>{


}

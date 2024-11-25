package com.citronix.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citronix.model.Tree;

/**
 * Repository interface for Tree entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface TreeRepository extends JpaRepository<Tree, Long>{

    long countByFieldId(Long fieldId);
    Page<Tree> findAll(Pageable pageable);
    List<Tree> findByFieldId(Long fieldId);
}

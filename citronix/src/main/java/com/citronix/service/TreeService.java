package com.citronix.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citronix.exception.business.FieldConstraintViolationException;
import com.citronix.exception.business.ResourceNotFoundException;
import com.citronix.model.Tree;
import com.citronix.model.enums.Level;
import com.citronix.repository.FieldRepository;
import com.citronix.repository.TreeRepository;

import jakarta.transaction.Transactional;

/**
 * Service interface for Tree entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
public class TreeService {
    private final TreeRepository treeRepository;
    private final FieldRepository fieldRepository;

    @Autowired
    public TreeService(TreeRepository treeRepository , FieldRepository fieldRepository){
        this.treeRepository =treeRepository;
        this.fieldRepository = fieldRepository;
    }

    @Transactional
    public Tree createTree(Long fieldId, LocalDate planedAt) {

        validatePlantingPeriod(planedAt);

        var field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new ResourceNotFoundException("Field not found"));

                
        validateFieldCapacity(field.getId(), field.getFieldArea());

        Tree tree = new Tree();
        tree.setPlantedAt(planedAt);
        tree.setLevel(calculateTreeLevel(planedAt));
        tree.setField(field);

        return treeRepository.save(tree);
    }


    private void validatePlantingPeriod(LocalDate planedAt) {
        int month = planedAt.getMonthValue();
        if (month < 3 || month > 5) {
            throw new FieldConstraintViolationException("Trees can only be planted between March and May.");
        }
    }

    private void validateFieldCapacity(Long fieldId, double fieldArea) {
        long currentTreeCount = treeRepository.countByFieldId(fieldId);
        int maxTrees = (int) (fieldArea * 1000 / 100);
        if (currentTreeCount >= maxTrees) {
            throw new FieldConstraintViolationException("Field capacity exceeded. Maximum density is 10 trees per 1,000 m².");
        }
    }

    private Level calculateTreeLevel(LocalDate planedAt) {
        int age = LocalDate.now().getYear() - planedAt.getYear();
        if (age < 3) {
            return Level.YOUNG;
        } else if (age <= 10) {
            return Level.MATURE;
        } else if (age <= 20) {
            return Level.OLD;
        } else {
            throw new FieldConstraintViolationException("Trees cannot be productive beyond 20 years.");
        }
    }

}

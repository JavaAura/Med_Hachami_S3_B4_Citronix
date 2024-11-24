package com.citronix.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.citronix.dto.req.TreeDTO;
import com.citronix.dto.req.TreeUpdateDTO;
import com.citronix.dto.res.TreeResponseDTO;
import com.citronix.exception.business.FieldConstraintViolationException;
import com.citronix.exception.business.ResourceNotFoundException;
import com.citronix.mapper.TreeMapperDTO;
import com.citronix.model.Field;
import com.citronix.model.Tree;
import com.citronix.model.enums.Level;
import com.citronix.repository.FieldRepository;
import com.citronix.repository.TreeRepository;
import com.citronix.service.interfaces.ITreeService;



/**
 * Service interface for Tree entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
public class TreeService implements ITreeService {
    private final TreeRepository treeRepository;
    private final FieldRepository fieldRepository;

    @Autowired
    public TreeService(TreeRepository treeRepository , FieldRepository fieldRepository ){
        this.treeRepository =treeRepository;
        this.fieldRepository = fieldRepository;
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

    @Override
    public TreeResponseDTO createTree(TreeDTO treeDTO) {
        Long fieldId = treeDTO.getFieldId();
        LocalDate planedAt = treeDTO.getPlantedAt();
        validatePlantingPeriod(planedAt);

        var field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new ResourceNotFoundException("Field not found"));

                
        validateFieldCapacity(field.getId(), field.getFieldArea());

        Tree tree = new Tree();
        tree.setPlantedAt(planedAt);
        tree.setLevel(calculateTreeLevel(planedAt));
        tree.setField(field);

        return TreeMapperDTO.INSTANCE.toDTO(treeRepository.save(tree));
    }

   

    public TreeResponseDTO findTreeById(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tree not found with ID: " + id));
        return TreeMapperDTO.INSTANCE.toDTO(tree);
    }

    @Override
    public void deleteTreeById(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tree not found with ID: " + id));
        
        treeRepository.delete(tree);
    }



    @Override
    public TreeResponseDTO updateTree(Long id, TreeUpdateDTO treeUpdateDTO) {
        Tree tree = treeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Tree not found with ID: " + id));

        Field field = fieldRepository.findById(treeUpdateDTO.getFieldId())
                .orElseThrow(() -> new ResourceNotFoundException("Field not found with ID: " + treeUpdateDTO.getFieldId()));

        tree.setPlantedAt(treeUpdateDTO.getPlantedAt());
        tree.setLevel(treeUpdateDTO.getLevel());
        tree.setField(field);

        Tree updatedTree = treeRepository.save(tree);

        return TreeMapperDTO.INSTANCE.toDTO(updatedTree);
    }



    @Override
    public Page<TreeResponseDTO> getAllTrees(int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException("Page index must be greater than or equal to 0");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Page size must be greater than 0");
        }
        
        Pageable pageable = PageRequest.of(page, size);
        
        return treeRepository.findAll(pageable).map(t->TreeMapperDTO.INSTANCE.toDTO(t));
    }



}

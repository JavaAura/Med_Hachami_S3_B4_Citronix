package com.citronix.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citronix.dto.req.FieldDTO;
import com.citronix.dto.res.FieldDisplayDTO;
import com.citronix.exception.business.FieldConstraintViolationException;
import com.citronix.exception.business.ResourceNotFoundException;
import com.citronix.mapper.FieldMapperDTO;
import com.citronix.model.Farm;
import com.citronix.model.Field;
import com.citronix.repository.FarmRepository;
import com.citronix.repository.FieldRepository;
import com.citronix.service.interfaces.IFieldService;

/**
 * Service interface for Field entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
public class FieldService implements IFieldService {

    private static final Logger log = LogManager.getLogger(FieldService.class);
    private final FieldRepository fieldRepository;
    private final FarmRepository farmRepository;

    @Autowired
    public FieldService(FieldRepository fieldRepository , FarmRepository farmRepository){
        this.fieldRepository = fieldRepository;
        this.farmRepository = farmRepository;
    }
    @Override
    public FieldDisplayDTO saveField(FieldDTO fieldDTO) {
        Farm farm = farmRepository.findById(fieldDTO.getFarmId())
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found"));

        validateFieldConstraints(fieldDTO, farm);
        Double totalFieldArea = fieldRepository.sumFieldAreasByFarmId(farm.getId());
        totalFieldArea = totalFieldArea != null ? totalFieldArea + fieldDTO.getArea() : fieldDTO.getArea();

        if (totalFieldArea >= farm.getFarmSurface()) {
            throw new IllegalArgumentException(
                    "The total area of fields cannot exceed the farm's surface area."
            );
        }

        Field field = new Field();
        field.setFieldName(fieldDTO.getName());
        field.setFieldArea(fieldDTO.getArea());
        field.setFarm(farm);

        Field savedField = fieldRepository.save(field);

        return FieldMapperDTO.INSTANCE.toDTO(savedField);
    }

    public List<FieldDisplayDTO> findFieldsByFarmId(Long farmId) {
        List<Field> fields = fieldRepository.findByFarmId(farmId);
        return fields.stream()
                .map(FieldMapperDTO.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FieldDisplayDTO getFieldById(Long fieldId) {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new ResourceNotFoundException("Field not found"));

        return FieldMapperDTO.INSTANCE.toDTO(field);

    }

    @Override
    public List<FieldDisplayDTO> findAllFields() {
        List<Field> fields = fieldRepository.findAll(); 
        return fields.stream()
                .map(f->FieldMapperDTO.INSTANCE.toDTO(f))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFieldById(Long id) {
        if (!fieldRepository.existsById(id)) {
            throw new ResourceNotFoundException("Field not found");
        }

        fieldRepository.deleteById(id);
    }

    @Override
    public FieldDisplayDTO updateField(Long fieldId, FieldDTO fieldDTO) {
        Field existingField = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new ResourceNotFoundException("Field not found with id: " + fieldId));

        Farm farm = existingField.getFarm();

        existingField.setFieldName(fieldDTO.getName());
        existingField.setFieldArea(fieldDTO.getArea());
        existingField.setFieldName(fieldDTO.getName());


        Field updatedField = fieldRepository.save(existingField);

        return FieldMapperDTO.INSTANCE.toDTO(updatedField);
    }


    private void validateFieldConstraints(FieldDTO fieldDTO, Farm farm) {

        if (fieldDTO.getArea() > (farm.getFarmSurface() * 0.5)) {
            throw new FieldConstraintViolationException("Field area cannot exceed 50% of the farm's total surface area.");
        }

        long fieldCount = fieldRepository.countByFarmId(farm.getId());
        if (fieldCount >= 10) {
            throw new FieldConstraintViolationException("A farm cannot have more than 10 fields.");
        }
    }
}

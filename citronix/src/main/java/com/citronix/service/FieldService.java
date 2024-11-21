package com.citronix.service;

import com.citronix.dto.req.FieldDTO;
import com.citronix.dto.res.FieldDisplayDTO;
import com.citronix.mapper.FieldMapperDTO;
import com.citronix.model.Farm;
import com.citronix.model.Field;
import com.citronix.repository.FarmRepository;
import com.citronix.repository.FieldRepository;
import com.citronix.service.interfaces.IFieldService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new RuntimeException("Farm not found"));

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
}

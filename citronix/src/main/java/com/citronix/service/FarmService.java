package com.citronix.service;

import com.citronix.dao.FarmDao;
import com.citronix.dto.req.FarmDTO;
import com.citronix.exception.business.DatabaseOperationException;
import com.citronix.exception.business.ResourceNotFoundException;
import com.citronix.mapper.FarmMapperDTO;
import com.citronix.model.Farm;
import com.citronix.repository.FarmRepository;
import com.citronix.service.interfaces.IFarmService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service interface for Farm entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
public class FarmService implements IFarmService {
    private static final Logger log = LogManager.getLogger(FarmService.class);
    private final FarmRepository farmRepository;
    private final FarmDao farmDao;


    @Autowired
    public FarmService(FarmRepository farmRepository,FarmDao farmDao){
        this.farmRepository = farmRepository;
        this.farmDao = farmDao;
    }


    @Override
    public Farm saveFarm(FarmDTO farmDTO) {
        
        Farm farm = FarmMapperDTO.INSTANCE.toEntity(farmDTO);

        try {
            return farmRepository.save(farm);
        }catch (Exception e){
            log.error("Error in adding trainer", e);
            throw new DatabaseOperationException("Failed to add trainer to the database", e);
        }
    }



    @Override
    public List<FarmDTO> findFarmByNameAndAddress(String farmName, String farmAddress) {
        return farmDao.findFarmByNameAndAddress(farmName, farmAddress);
    }

    @Transactional
    @Override
    public FarmDTO updateFarm(FarmDTO farmDTO) {
        Farm farm = farmRepository.findById(farmDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Farm not found"));

        Farm updatedFarm = farmRepository.save(FarmMapperDTO.INSTANCE.toEntity(farmDTO));

        return FarmMapperDTO.INSTANCE.toDTO(updatedFarm);
    }

    public FarmDTO getFarmById(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Farm not found"));

        return FarmMapperDTO.INSTANCE.toDTO(farm);
    }

    public void deleteFarmById(Long id) {
        if (!farmRepository.existsById(id)) {
            throw new IllegalArgumentException("Farm not found");
        }

        farmRepository.deleteById(id);
    }


}

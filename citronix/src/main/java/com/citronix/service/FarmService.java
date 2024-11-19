package com.citronix.service;

import com.citronix.dao.FarmDao;
import com.citronix.dto.req.FarmDTO;
import com.citronix.exception.business.DatabaseOperationException;
import com.citronix.exception.business.ResourceNotFoundException;
import com.citronix.mapper.FarmMapperDTO;
import com.citronix.model.Farm;
import com.citronix.repository.FarmRepository;
import com.citronix.service.interfaces.IFarmService;
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
    public List<FarmDTO> allFarms() {
        List<Farm> farms = farmRepository.findAll();
        if(farms.isEmpty()){
            throw new ResourceNotFoundException("No farms found");
        }
        return farms.stream().map(f->FarmMapperDTO.INSTANCE.toDTO(f)).collect(Collectors.toList());
    }

    @Override
    public List<FarmDTO> findFarmByNameAndAddress(String farmName, String farmAddress) {
        return farmDao.findFarmByNameAndAddress(farmName, farmAddress);
    }
}

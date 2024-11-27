package com.citronix.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.citronix.dto.req.HarvestDTO;
import com.citronix.dto.res.HarvestResponseDTO;
import com.citronix.mapper.HarvestMapper;
import com.citronix.model.Harvest;
import com.citronix.model.HarvestDetail;
import com.citronix.model.Tree;
import com.citronix.repository.HarvestDetailRepository;
import com.citronix.repository.HarvestRepository;
import com.citronix.repository.TreeRepository;
import com.citronix.service.interfaces.IHarvestService;

/**
 * Service interface for Harvest entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
public class HarvestService implements IHarvestService {
    private static final Logger log = LogManager.getLogger(HarvestService.class);
    private final HarvestRepository harvestRepository;
    private final HarvestDetailRepository harvestDetailRepository;
    private final TreeRepository treeRepository;



    public HarvestService(HarvestRepository harvestRepository,HarvestDetailRepository harvestDetailRepository,TreeRepository treeRepository){
        this.harvestRepository = harvestRepository;
        this.harvestDetailRepository = harvestDetailRepository;
        this.treeRepository = treeRepository;
    }

    @Override
    public HarvestResponseDTO createHarvest(HarvestDTO harvestDTO) {
    
        // Check if a harvest already exists for the given field and season
        boolean exists = harvestRepository.existsByFieldIdAndSeason(harvestDTO.getFieldId(), harvestDTO.getSeason());
        if (exists) {
            throw new IllegalStateException("A harvest already exists for this field and season.");
        }
    
        // Fetch trees for the provided field ID
        List<Tree> trees = treeRepository.findByFieldId(harvestDTO.getFieldId());
        if (trees.isEmpty()) {
            throw new IllegalArgumentException("No trees found for the provided field ID.");
        }
    
        // Calculate total quantity and prepare harvest details
        Integer totalQuantity = 0;
        List<HarvestDetail> harvestDetails = new ArrayList<>();
        for (Tree tree : trees) {
            int quantity = tree.calculateProductivity();
    
            HarvestDetail harvestDetail = new HarvestDetail();
            harvestDetail.setTree(tree);
            harvestDetail.setQuantity(quantity);
    
            harvestDetails.add(harvestDetail);
            totalQuantity += quantity;
        }
    
        // Create and set up the Harvest entity
        Harvest harvest = HarvestMapper.INSTANCE.toEntity(harvestDTO);
        log.info("Total quanti"+totalQuantity);
        harvest.setTotalQuantity(totalQuantity);
        harvest.setHarvestDate(harvestDTO.getHarvestDate());
    
        // Save the Harvest entity
        harvest = harvestRepository.save(harvest);
    
        // Link HarvestDetails to the saved Harvest and save them
        for (HarvestDetail harvestDetail : harvestDetails) {
            harvestDetail.setHarvest(harvest);
        }
        harvestDetailRepository.saveAll(harvestDetails);
    
        // Return response DTO
        return HarvestMapper.INSTANCE.toDTO(harvest);
    }

    @Override
    public Page<HarvestResponseDTO> findAllHarvests(Pageable pageable) {
        Page<Harvest> harvestPage = harvestRepository.findAll(pageable);
        return harvestPage.map(HarvestMapper.INSTANCE::toDTO);
    }

    @Override
    public HarvestResponseDTO findHarvestById(Long id) {
         Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Harvest not found with ID: " + id));
        return HarvestMapper.INSTANCE.toDTO(harvest);
    }

    @Override
    public void deleteHarvest(Long id) {
        Harvest harvest = harvestRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Harvest not found with ID: " + id));

        harvestRepository.delete(harvest);
        
    }
    

    


}

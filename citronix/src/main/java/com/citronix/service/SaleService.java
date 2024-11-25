package com.citronix.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citronix.dto.req.SaleDTO;
import com.citronix.dto.res.SaleResponseDTO;
import com.citronix.exception.business.ResourceNotFoundException;
import com.citronix.mapper.SaleMapper;
import com.citronix.model.Harvest;
import com.citronix.model.Sale;
import com.citronix.repository.HarvestRepository;
import com.citronix.repository.SaleRepository;
import com.citronix.service.interfaces.ISaleService;

/**
 * Service interface for Sale entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
public class SaleService implements ISaleService {

    private final SaleRepository saleRepository;
    private final HarvestRepository harvestRepository;

    public SaleService(SaleRepository saleRepository, HarvestRepository harvestRepository){
        this.saleRepository = saleRepository;
        this.harvestRepository = harvestRepository;
    }

    @Transactional
    @Override
    public SaleResponseDTO addSale(SaleDTO saleDTO) {
        Harvest harvest = harvestRepository.findById(saleDTO.getHarvestId())
                .orElseThrow(() -> new RuntimeException("Harvest not found with ID: " + saleDTO.getHarvestId()));

        Sale sale = SaleMapper.INSTANCE.toEntity(saleDTO);
        sale.calculateRevenue();
        sale.setHarvest(harvest);

        return SaleMapper.INSTANCE.toDTO(saleRepository.save(sale));
    }

    @Override
    public SaleResponseDTO findSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with ID: " + id));
        return SaleMapper.INSTANCE.toDTO(sale);
    }

    @Override
    public void deleteSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with ID: " + id));
        
        saleRepository.delete(sale);
    }

    @Override
    public Page<SaleResponseDTO> getAllSales(int page, int size) {
       if (page < 0) {
            throw new IllegalArgumentException("Page index must be greater than or equal to 0");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Page size must be greater than 0");
        }
        
        Pageable pageable = PageRequest.of(page, size);
        
        return saleRepository.findAll(pageable).map(s->SaleMapper.INSTANCE.toDTO(s));
    }
}

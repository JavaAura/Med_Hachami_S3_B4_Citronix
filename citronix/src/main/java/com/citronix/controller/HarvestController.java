package com.citronix.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citronix.dto.req.HarvestDTO;
import com.citronix.dto.res.HarvestResponseDTO;
import com.citronix.service.HarvestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * REST controller for managing Harvest entities.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/harvests")
public class HarvestController {
    private final HarvestService harvestService;
    private static final Logger log = LogManager.getLogger(HarvestController.class);

    @Autowired
    public HarvestController(HarvestService harvestService){
        this.harvestService = harvestService;
    }


    @Operation(summary = "Create a new harvest", description = "Creates a new harvest by specifying the field ID, season, and harvest date.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Harvest created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input provided"),
        @ApiResponse(responseCode = "404", description = "Field not found for the provided ID")
    })
    @PostMapping
    public ResponseEntity<HarvestResponseDTO> createHarvest(@RequestBody HarvestDTO harvestDTO) {
        log.info("harvestDate"+harvestDTO.getHarvestDate());
        log.info("season"+harvestDTO.getSeason());

        HarvestResponseDTO harvestResponseDTO = harvestService.createHarvest(harvestDTO);
        return new ResponseEntity<>(harvestResponseDTO, HttpStatus.CREATED);
    }


    @Operation(summary = "Get all harvests with pagination and sorting", description = "Fetches all harvests with optional pagination and sorting parameters.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Harvests fetched successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    @GetMapping
    public ResponseEntity<Page<HarvestResponseDTO>> getAllHarvests(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "harvestDate") String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection) {
        
        // Determine sort direction
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);

        // Create Pageable object with sorting
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        // Fetch harvests using the service
        Page<HarvestResponseDTO> harvestPage = harvestService.findAllHarvests(pageable);

        return new ResponseEntity<>(harvestPage, HttpStatus.OK);
    }

    @Operation(summary = "Get harvest by ID", description = "Fetches a specific harvest by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Harvest fetched successfully"),
        @ApiResponse(responseCode = "404", description = "Harvest not found for the provided ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HarvestResponseDTO> getHarvestById(@PathVariable("id") Long id) {
        log.info("Fetching harvest with ID: " + id);

        HarvestResponseDTO harvestResponseDTO = harvestService.findHarvestById(id);


        if (harvestResponseDTO != null) {
            return new ResponseEntity<>(harvestResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }

    @Operation(summary = "Delete a harvest", description = "Deletes a harvest by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Harvest deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Harvest not found with the provided ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvest(@PathVariable Long id) {
        try {
            log.info("Deleting harvest with ID: {}", id);
            harvestService.deleteHarvest(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  
        } catch (RuntimeException e) {
            log.error("Error deleting harvest with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
        }
    }


}

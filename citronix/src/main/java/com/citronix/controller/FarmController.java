package com.citronix.controller;

import com.citronix.dto.req.FarmDTO;
import com.citronix.mapper.FarmMapperDTO;
import com.citronix.model.Farm;
import com.citronix.service.FarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Farm entities.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/farms")
public class FarmController {
    private static final Logger log = LogManager.getLogger(FarmController.class);

    private final FarmService farmService;

    @Autowired
    public FarmController(FarmService farmService){
        this.farmService = farmService;
    }

    /**
     * Create a new Farm.
     *
     * @param farmDto the Farm data to save
     * @return the saved Farm entity
     */
    @PostMapping
    @Operation(summary = "Create a new Farm", description = "Adds a new farm to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Farm created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FarmDTO.class))),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public ResponseEntity<FarmDTO> addFarm(@Valid @RequestBody FarmDTO farmDto) {
        log.info("Adding a new farm: {}", farmDto.getName());
        Farm savedFarm = farmService.saveFarm(farmDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(FarmMapperDTO.INSTANCE.toDTO(savedFarm));
    }


    @GetMapping("/search")
    @Operation(
            summary = "Search farms by name and address",
            description = "Search for farms by their name and address. If either parameter is empty, all farms will be returned.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the list of farms",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = FarmDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request - Invalid parameters",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    public ResponseEntity<List<FarmDTO>> findFarmByNameAndAddress(
            @Parameter(description = "Name of the farm to search for", required = true)
            @RequestParam String farmName,

            @Parameter(description = "Address of the farm to search for", required = true)
            @RequestParam @NotEmpty String farmAddress) {

        List<FarmDTO> farmDTOs = farmService.findFarmByNameAndAddress(farmName, farmAddress);

        return ResponseEntity.ok(farmDTOs);
    }

    




}

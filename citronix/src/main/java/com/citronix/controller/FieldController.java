package com.citronix.controller;

import com.citronix.dto.req.FieldDTO;
import com.citronix.dto.req.FarmDTO;
import com.citronix.dto.res.FieldDisplayDTO;
import com.citronix.mapper.FarmMapperDTO;
import com.citronix.mapper.FieldMapperDTO;
import com.citronix.model.Farm;
import com.citronix.model.Field;
import com.citronix.service.FieldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Field entities.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/fields")
public class FieldController {

    private static final Logger log = LogManager.getLogger(FieldController.class);
    private final FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService){
        this.fieldService = fieldService;
    }

    /**
     * Create a new field.
     *
     * @param fieldDTO the Field data to save
     * @return the saved Field entity
     */
    @PostMapping
    @Operation(summary = "Create a new Farm", description = "Adds a new farm to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Farm created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FarmDTO.class))),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public ResponseEntity<FieldDisplayDTO> addField(@Valid @RequestBody FieldDTO fieldDTO) {
        FieldDisplayDTO savedField = fieldService.saveField(fieldDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedField);
    }


}

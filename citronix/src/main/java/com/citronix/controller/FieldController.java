package com.citronix.controller;

import com.citronix.dto.req.FieldDTO;
import com.citronix.dto.req.FarmDTO;
import com.citronix.dto.res.ErrorResponse;
import com.citronix.dto.res.FieldDisplayDTO;
import com.citronix.exception.business.FieldConstraintViolationException;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @Operation(
            summary = "Get fields by farm ID",
            description = "Retrieve a list of fields associated with a specific farm ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of fields retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Farm not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/farm/{farmId}")
    public List<FieldDisplayDTO> getFieldsByFarmId(@PathVariable Long farmId) {
        return fieldService.findFieldsByFarmId(farmId);
    }

    @Operation(summary = "Get field details by ID", description = "Fetch field details  by field ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Field details fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Field ID"),
            @ApiResponse(responseCode = "404", description = "Field not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FieldDisplayDTO> getFieldById(@PathVariable Long id) {
        FieldDisplayDTO fieldDto = fieldService.getFieldById(id);

        return ResponseEntity.ok(fieldDto);
    }

    @Operation(
            summary = "Get all fields",
            description = "Retrieve a list of all fields available in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all fields retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<FieldDisplayDTO> getAllFields() {
        return fieldService.findAllFields();
    }


    @Operation(summary = "Delete a field by ID", description = "Delete a field using its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Field deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid field ID"),
            @ApiResponse(responseCode = "404", description = "Field not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFieldById(@PathVariable Long id) {
        fieldService.deleteFieldById(id);

        return ResponseEntity.ok("Field deleted successfully");
    }

    @PutMapping("/{fieldId}")
    public ResponseEntity<?> updateField(
            @PathVariable Long fieldId,
            @RequestBody FieldDTO fieldDTO
    ) {
        try {
            FieldDisplayDTO updatedField = fieldService.updateField(fieldId, fieldDTO);
            return ResponseEntity.ok(updatedField);
        } catch (FieldConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred");
        }
    }


}

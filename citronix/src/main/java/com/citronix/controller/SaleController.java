package com.citronix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.citronix.dto.req.SaleDTO;
import com.citronix.dto.res.SaleResponseDTO;
import com.citronix.service.SaleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * REST controller for managing Sale entities.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/sales")
public class SaleController {   

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }
    

    @PostMapping
    @Operation(summary = "Add a new sale", description = "Creates a new sale record with details like date, price, quantity, and associated harvest.")
    @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Tree created successfully",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = SaleResponseDTO.class))),
                @ApiResponse(responseCode = "404", description = "Field not found", content = @Content),
                @ApiResponse(responseCode = "400", description = "Invalid planting period or field capacity exceeded", content = @Content)
    })
    public ResponseEntity<SaleResponseDTO> addSale(@RequestBody SaleDTO saleDTO) {
        SaleResponseDTO sale = saleService.addSale(saleDTO);
        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Sale by ID", description = "Fetch a sale by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sale found"),
        @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    @GetMapping("/{id}")
    public SaleResponseDTO getSaleById(@PathVariable("id") Long id) {
        return saleService.findSaleById(id);
    }


    @Operation(
    summary = "Delete Sale by ID",
    description = "Deletes a specific tree by its ID."
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully deleted the sale", content = @Content),
    @ApiResponse(responseCode = "404", description = "Sale not found", content = @Content),
    @ApiResponse(responseCode = "500", description = "Internal server error occurred", content = @Content)
    })
    @DeleteMapping("/trees/{id}")
    public ResponseEntity<String> deleteSaleById(@PathVariable Long id) {
            saleService.deleteSaleById(id);
            return ResponseEntity.ok("Sale deleted successfully");
    }

     @Operation(
    summary = "Get all sales with pagination",
    description = "Retrieve a paginated list of sales. The page and size parameters allow for specifying the pagination settings.",
    responses = {
        @ApiResponse(
            responseCode = "200",
            description = "List of trees retrieved successfully",
            content = @Content(
                schema = @Schema(implementation = SaleResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid page or size parameters"
        )
    }
    )
    @GetMapping
    public Page<SaleResponseDTO> getAllTrees(@RequestParam int page, @RequestParam int size) {
            return saleService.getAllSales(page, size);
    }
}

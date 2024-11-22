package com.citronix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citronix.dto.req.TreeDTO;
import com.citronix.dto.res.TreeResponseDTO;
import com.citronix.mapper.TreeMapperDTO;
import com.citronix.model.Tree;
import com.citronix.service.TreeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * REST controller for managing Tree entities.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/trees")
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService){
        this.treeService = treeService;
    }


     @Operation(summary = "Create a new tree", description = "Plant a new tree in a specified field.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tree created successfully", 
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tree.class))),
            @ApiResponse(responseCode = "404", description = "Field not found", 
                         content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid planting period or field capacity exceeded", 
                         content = @Content)
    })
    @PostMapping
    public ResponseEntity<TreeResponseDTO> createTree(@RequestBody @Valid TreeDTO treeCreateDTO) {
        Tree tree = treeService.createTree(treeCreateDTO.getFieldId(), treeCreateDTO.getPlantedAt());
        TreeResponseDTO responseDTO = TreeMapperDTO.INSTANCE.toDTO(tree);
        return ResponseEntity.status(201).body(responseDTO);
    }
    }

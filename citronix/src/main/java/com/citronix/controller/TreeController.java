package com.citronix.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citronix.dto.req.TreeDTO;
import com.citronix.dto.req.TreeUpdateDTO;
import com.citronix.dto.res.TreeResponseDTO;
import com.citronix.model.Tree;
import com.citronix.service.TreeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/trees")
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

        @Operation(summary = "Create a new tree", description = "Plant a new tree in a specified field.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Tree created successfully",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tree.class))),
                @ApiResponse(responseCode = "404", description = "Field not found", content = @Content),
                @ApiResponse(responseCode = "400", description = "Invalid planting period or field capacity exceeded", content = @Content)
        })
        @PostMapping("/trees")
        public ResponseEntity<TreeResponseDTO> createTree(@Valid @RequestBody TreeDTO treeDTO) {
                TreeResponseDTO treeResponse = treeService.createTree(treeDTO);
                return ResponseEntity.ok(treeResponse);
        }
        
        
        @Operation(
        summary = "Get all trees with pagination",
        description = "Retrieve a paginated list of trees. The page and size parameters allow for specifying the pagination settings.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "List of trees retrieved successfully",
                content = @Content(
                    schema = @Schema(implementation = TreeResponseDTO.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid page or size parameters"
            )
        }
        )
        @GetMapping
        public Page<TreeResponseDTO> getAllTrees(@RequestParam int page, @RequestParam int size) {
                return treeService.getAllTrees(page, size); 
        }
        

        @Operation(
                summary = "Get Tree by ID",
                description = "Fetches a specific tree by its ID and returns it as a TreeResponseDTO."
        )
        @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the tree",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = TreeResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Tree not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error occurred", content = @Content)
        })
        @GetMapping("/trees/{id}")
        public ResponseEntity<TreeResponseDTO> getTreeById(@PathVariable Long id) {
                TreeResponseDTO tree = treeService.findTreeById(id);
                return ResponseEntity.ok(tree);
        }

        @Operation(
        summary = "Delete Tree by ID",
        description = "Deletes a specific tree by its ID."
        )
        @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted the tree", content = @Content),
        @ApiResponse(responseCode = "404", description = "Tree not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error occurred", content = @Content)
        })
        @DeleteMapping("/trees/{id}")
        public ResponseEntity<String> deleteTreeById(@PathVariable Long id) {
                treeService.deleteTreeById(id);
                return ResponseEntity.ok("Tree deleted successfully");
        }


        @Operation(
        summary = "Update Tree by ID",
        description = "Updates the details of an existing tree by its ID."
        )
        @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated the tree",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = TreeResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Tree not found", content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error occurred", content = @Content)
        })
        @PutMapping("/trees/{id}")
        public ResponseEntity<TreeResponseDTO> updateTree(
        @PathVariable Long id,
        @Valid @RequestBody TreeUpdateDTO treeUpdateDTO) {
                TreeResponseDTO updatedTree = treeService.updateTree(id, treeUpdateDTO);
                return ResponseEntity.ok(updatedTree);
        }

}

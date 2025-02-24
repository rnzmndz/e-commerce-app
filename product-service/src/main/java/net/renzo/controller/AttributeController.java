package net.renzo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renzo.dto.AttributeDTO;
import net.renzo.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/attributes")
@Tag(name = "Attribute Controller", description = "Endpoints for managing product attributes")
public class AttributeController {

    private final AttributeService attributeService;

    @Autowired
    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @Operation(summary = "Find attribute by ID",
            description = "Retrieves a specific attribute by its ID")
    @ApiResponse(responseCode = "200", description = "Attribute found")
    @ApiResponse(responseCode = "404", description = "Attribute not found")
    @GetMapping("/{id}")
    public ResponseEntity<AttributeDTO> findById(@Parameter(description = "ID of the attribute") @PathVariable Long id) {
        Optional<AttributeDTO> attributeDTO = attributeService.findById(id);
        return attributeDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all attributes",
            description = "Retrieves a paginated list of all attributes")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved attributes")
    @GetMapping
    public ResponseEntity<Page<AttributeDTO>> findAll(Pageable pageable) {
        Page<AttributeDTO> attributes = attributeService.findAll(pageable);
        return ResponseEntity.ok(attributes);
    }

    @Operation(summary = "Update attribute",
            description = "Updates an existing attribute by its ID")
    @ApiResponse(responseCode = "200", description = "Attribute updated successfully")
    @ApiResponse(responseCode = "404", description = "Attribute not found")
    @PutMapping("/{id}")
    public ResponseEntity<AttributeDTO> update(
            @Parameter(description = "ID of the attribute to update") @PathVariable Long id,
            @Parameter(description = "Updated attribute details") @RequestBody AttributeDTO attributeDTO) {
        attributeDTO.setId(id);
        AttributeDTO updatedAttribute = attributeService.update(attributeDTO);
        return ResponseEntity.ok(updatedAttribute);
    }

    @Operation(summary = "Add attribute to product",
            description = "Associates an attribute with a specific product")
    @ApiResponse(responseCode = "204", description = "Attribute added to product successfully")
    @ApiResponse(responseCode = "404", description = "Product or attribute not found")
    @PostMapping("/{productId}/add/{attributeId}")
    public ResponseEntity<Void> addAttributeToProduct(
            @Parameter(description = "ID of the product") @PathVariable Long productId,
            @Parameter(description = "ID of the attribute to add") @PathVariable Long attributeId) {
        attributeService.addAttributeToProduct(productId, attributeId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove attribute from product",
            description = "Removes an attribute association from a specific product")
    @ApiResponse(responseCode = "204", description = "Attribute removed from product successfully")
    @ApiResponse(responseCode = "404", description = "Product or attribute not found")
    @DeleteMapping("/{productId}/remove/{attributeId}")
    public ResponseEntity<Void> removeAttributeFromProduct(
            @Parameter(description = "ID of the product") @PathVariable Long productId,
            @Parameter(description = "ID of the attribute to remove") @PathVariable Long attributeId) {
        attributeService.removeAttributeFromProduct(productId, attributeId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete attribute",
            description = "Deletes an attribute by its ID")
    @ApiResponse(responseCode = "204", description = "Attribute deleted successfully")
    @ApiResponse(responseCode = "404", description = "Attribute not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Parameter(description = "ID of the attribute to delete") @PathVariable Long id) {
        attributeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
package net.renzo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renzo.dto.VariantDTO;
import net.renzo.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/variants")
@Tag(name = "Variant Controller", description = "Endpoints for managing product variants")
public class VariantController {

    private final VariantService variantService;

    @Autowired
    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    @Operation(summary = "Create new variant",
            description = "Creates a new product variant with the provided details")
    @ApiResponse(responseCode = "200", description = "Variant created successfully")
    @PostMapping
    public ResponseEntity<VariantDTO> save(
            @Parameter(description = "Variant details") @RequestBody VariantDTO variantDTO) {
        VariantDTO createdVariant = variantService.createProductVariant(variantDTO);
        return ResponseEntity.ok(createdVariant);
    }

    @Operation(summary = "Find variant by ID",
            description = "Retrieves a specific product variant by its ID")
    @ApiResponse(responseCode = "200", description = "Variant found")
    @ApiResponse(responseCode = "404", description = "Variant not found")
    @GetMapping("/{id}")
    public ResponseEntity<VariantDTO> findById(
            @Parameter(description = "ID of the variant") @PathVariable Long id) {
        Optional<VariantDTO> variantDTO = variantService.getProductVariantById(id);
        return variantDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all variants",
            description = "Retrieves a paginated list of all product variants")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved variants")
    @GetMapping
    public ResponseEntity<Page<VariantDTO>> findAll(Pageable pageable) {
        Page<VariantDTO> variants = variantService.getAllProductVariants(pageable);
        return ResponseEntity.ok(variants);
    }

    @Operation(summary = "Update variant",
            description = "Updates an existing product variant by its ID")
    @ApiResponse(responseCode = "200", description = "Variant updated successfully")
    @ApiResponse(responseCode = "404", description = "Variant not found")
    @PutMapping("/{id}")
    public ResponseEntity<VariantDTO> update(
            @Parameter(description = "ID of the variant to update") @PathVariable Long id,
            @Parameter(description = "Updated variant details") @RequestBody VariantDTO variantDTO) {
        VariantDTO updatedVariant = variantService.updateProductVariant(id, variantDTO);
        return ResponseEntity.ok(updatedVariant);
    }

    @Operation(summary = "Delete variant",
            description = "Deletes a product variant by its ID")
    @ApiResponse(responseCode = "204", description = "Variant deleted successfully")
    @ApiResponse(responseCode = "404", description = "Variant not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the variant to delete") @PathVariable Long id) {
        variantService.deleteProductVariant(id);
        return ResponseEntity.noContent().build();
    }
}
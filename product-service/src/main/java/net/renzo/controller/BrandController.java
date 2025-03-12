package net.renzo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renzo.dto.BrandDTO;
import net.renzo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/brands")
@Tag(name = "Brand Controller", description = "Endpoints for managing brands")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Operation(summary = "Find brand by ID",
            description = "Retrieves a specific brand by its ID")
    @ApiResponse(responseCode = "200", description = "Brand found")
    @ApiResponse(responseCode = "404", description = "Brand not found")
    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> findById(@Parameter(description = "ID of the brand") @PathVariable Long id) {
        Optional<BrandDTO> brand = brandService.findBrandById(id);
        return brand.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all brands",
            description = "Retrieves a paginated list of all brands")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved brands")
    @GetMapping
    public ResponseEntity<Page<BrandDTO>> findAll(Pageable pageable) {
        Page<BrandDTO> brands = brandService.findAllBrands(pageable);
        return ResponseEntity.ok(brands);
    }

    @Operation(summary = "Update brand",
            description = "Updates an existing brand by its ID")
    @ApiResponse(responseCode = "200", description = "Brand updated successfully")
    @ApiResponse(responseCode = "404", description = "Brand not found")
    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> update(
            @Parameter(description = "ID of the brand to update") @PathVariable Long id,
            @Parameter(description = "Updated brand details") @RequestBody BrandDTO brandDTO) {
        brandDTO.setId(id);
        BrandDTO updatedBrand = brandService.updateBrand(id, brandDTO);
        return ResponseEntity.ok(updatedBrand);
    }

    @Operation(summary = "Delete brand",
            description = "Deletes a brand by its ID")
    @ApiResponse(responseCode = "204", description = "Brand deleted successfully")
    @ApiResponse(responseCode = "404", description = "Brand not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Parameter(description = "ID of the brand to delete") @PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
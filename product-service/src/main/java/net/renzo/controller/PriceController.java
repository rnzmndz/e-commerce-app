package net.renzo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renzo.dto.ImageDTO;
import net.renzo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/prices")
@Tag(name = "Price Controller", description = "Endpoints for managing prices")
public class PriceController {

    private final ImageService imageService;

    @Autowired
    public PriceController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Operation(summary = "Find price by ID",
            description = "Retrieves a specific price by its ID")
    @ApiResponse(responseCode = "200", description = "Price found")
    @ApiResponse(responseCode = "404", description = "Price not found")
    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> findById(
            @Parameter(description = "ID of the price") @PathVariable Long id) {
        Optional<ImageDTO> image = imageService.findById(id);
        return image.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all prices",
            description = "Retrieves a paginated list of all prices")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved prices")
    @GetMapping
    public ResponseEntity<Page<ImageDTO>> findAll(Pageable pageable) {
        Page<ImageDTO> images = imageService.findAll(pageable);
        return ResponseEntity.ok(images);
    }

    @Operation(summary = "Update price",
            description = "Updates an existing price by its ID")
    @ApiResponse(responseCode = "200", description = "Price updated successfully")
    @ApiResponse(responseCode = "404", description = "Price not found")
    @PutMapping("/{id}")
    public ResponseEntity<ImageDTO> update(
            @Parameter(description = "ID of the price to update") @PathVariable Long id,
            @Parameter(description = "Updated price details") @RequestBody ImageDTO imageDTO) {
        imageDTO.setId(id);
        ImageDTO updatedImage = imageService.update(id, imageDTO);
        return ResponseEntity.ok(updatedImage);
    }

    @Operation(summary = "Delete price",
            description = "Deletes a price by its ID")
    @ApiResponse(responseCode = "204", description = "Price deleted successfully")
    @ApiResponse(responseCode = "404", description = "Price not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID of the price to delete") @PathVariable Long id) {
        imageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
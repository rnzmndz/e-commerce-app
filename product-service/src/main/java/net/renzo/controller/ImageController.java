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
@RequestMapping("/images")
@Tag(name = "Image Controller", description = "Endpoints for managing images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Operation(summary = "Find image by ID",
            description = "Retrieves a specific image by its ID")
    @ApiResponse(responseCode = "200", description = "Image found")
    @ApiResponse(responseCode = "404", description = "Image not found")
    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> findById(
            @Parameter(description = "ID of the image") @PathVariable Long id) {
        Optional<ImageDTO> image = imageService.findById(id);
        return image.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all images",
            description = "Retrieves a paginated list of all images")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved images")
    @GetMapping
    public ResponseEntity<Page<ImageDTO>> findAll(Pageable pageable) {
        Page<ImageDTO> images = imageService.findAll(pageable);
        return ResponseEntity.ok(images);
    }

    @Operation(summary = "Update image",
            description = "Updates an existing image by its ID")
    @ApiResponse(responseCode = "200", description = "Image updated successfully")
    @ApiResponse(responseCode = "404", description = "Image not found")
    @PutMapping("/{id}")
    public ResponseEntity<ImageDTO> update(
            @Parameter(description = "ID of the image to update") @PathVariable Long id,
            @Parameter(description = "Updated image details") @RequestBody ImageDTO imageDTO) {
        imageDTO.setId(id);
        ImageDTO updatedImage = imageService.update(id, imageDTO);
        return ResponseEntity.ok(updatedImage);
    }

    @Operation(summary = "Delete image",
            description = "Deletes an image by its ID")
    @ApiResponse(responseCode = "204", description = "Image deleted successfully")
    @ApiResponse(responseCode = "404", description = "Image not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID of the image to delete") @PathVariable Long id) {
        imageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
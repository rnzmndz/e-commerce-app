package net.renzo.controller;

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
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> findById(@PathVariable Long id) {
        Optional<ImageDTO> image = imageService.findById(id);
        return image.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<ImageDTO>> findAll(Pageable pageable) {
        Page<ImageDTO> images = imageService.findAll(pageable);
        return ResponseEntity.ok(images);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageDTO> update(@PathVariable Long id, @RequestBody ImageDTO imageDTO) {
        imageDTO.setId(id);
        ImageDTO updatedImage = imageService.update(id, imageDTO);
        return ResponseEntity.ok(updatedImage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        imageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
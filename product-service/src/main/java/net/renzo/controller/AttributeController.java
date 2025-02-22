package net.renzo.controller;

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
public class AttributeController {

    private final AttributeService attributeService;

    @Autowired
    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeDTO> findById(@PathVariable Long id) {
        Optional<AttributeDTO> attributeDTO = attributeService.findById(id);
        return attributeDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<AttributeDTO>> findAll(Pageable pageable) {
        Page<AttributeDTO> attributes = attributeService.findAll(pageable);
        return ResponseEntity.ok(attributes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttributeDTO> update(@PathVariable Long id, @RequestBody AttributeDTO attributeDTO) {
        attributeDTO.setId(id);
        AttributeDTO updatedAttribute = attributeService.update(attributeDTO);
        return ResponseEntity.ok(updatedAttribute);
    }

    @PostMapping("/{productId}/add/{attributeId}")
    public ResponseEntity<Void> addAttributeToProduct(@PathVariable Long productId, @PathVariable Long attributeId) {
        attributeService.addAttributeToProduct(productId, attributeId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}/remove/{attributeId}")
    public ResponseEntity<Void> removeAttributeFromProduct(@PathVariable Long productId, @PathVariable Long attributeId) {
        attributeService.removeAttributeFromProduct(productId, attributeId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        attributeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
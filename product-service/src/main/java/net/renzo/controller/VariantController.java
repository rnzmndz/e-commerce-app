package net.renzo.controller;

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
public class VariantController {

    private final VariantService variantService;

    @Autowired
    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    @PostMapping
    public ResponseEntity<VariantDTO> save(@RequestBody VariantDTO variantDTO) {
        VariantDTO createdVariant = variantService.createProductVariant(variantDTO);
        return ResponseEntity.ok(createdVariant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariantDTO> findById(@PathVariable Long id) {
        Optional<VariantDTO> variantDTO = variantService.getProductVariantById(id);
        return variantDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<VariantDTO>> findAll(Pageable pageable) {
        Page<VariantDTO> variants = variantService.getAllProductVariants(pageable);
        return ResponseEntity.ok(variants);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VariantDTO> update(@PathVariable Long id, @RequestBody VariantDTO variantDTO) {
        VariantDTO updatedVariant = variantService.updateProductVariant(id, variantDTO);
        return ResponseEntity.ok(updatedVariant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        variantService.deleteProductVariant(id);
        return ResponseEntity.noContent().build();
    }
}
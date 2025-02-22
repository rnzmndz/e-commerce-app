package net.renzo.controller;

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
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> findById(@PathVariable Long id) {
        Optional<BrandDTO> brand = brandService.findBrandById(id);
        return brand.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<BrandDTO>> findAll(Pageable pageable) {
        Page<BrandDTO> brands = brandService.findAllBrands(pageable);
        return ResponseEntity.ok(brands);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> update(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        brandDTO.setId(id);
        BrandDTO updatedBrand = brandService.updateBrand(id, brandDTO);
        return ResponseEntity.ok(updatedBrand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
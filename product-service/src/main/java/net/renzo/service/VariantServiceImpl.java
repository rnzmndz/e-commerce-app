package net.renzo.service;

import net.renzo.dto.VariantDTO;
import net.renzo.exception.ProductNotFoundException;
import net.renzo.exception.VariantNotFoundException;
import net.renzo.mapper.VariantMapper;
import net.renzo.model.Variant;
import net.renzo.repository.ProductRepository;
import net.renzo.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VariantServiceImpl implements VariantService{

    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;
    private final ProductRepository productRepository;

    @Autowired
    public VariantServiceImpl(VariantRepository variantRepository, VariantMapper variantMapper, ProductRepository productRepository) {
        this.variantRepository = variantRepository;
        this.variantMapper = variantMapper;
        this.productRepository = productRepository;
    }
    @Override
    public VariantDTO createProductVariant(VariantDTO variantDTO) {
        // Convert DTO to entity
        Variant variant = variantMapper.toEntity(variantDTO);

        // Save entity to repository
        Variant savedVariant = variantRepository.save(variant);

        // Convert saved entity back to DTO
        return variantMapper.toDto(savedVariant);
    }

    @Override
    public Optional<VariantDTO> getProductVariantById(Long id) {
        // Retrieve the variant by ID
        Variant variant = variantRepository.findById(id)
                .orElseThrow(() -> new VariantNotFoundException("Variant not found with ID: " + id));

        // Convert entity to DTO
        return Optional.of(variantMapper.toDto(variant));
    }

    @Override
    public Page<VariantDTO> getProductVariantsByProductId(Long productId, Pageable pageable) {
        // Check if the product exists
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        // Retrieve the variants by product ID with pagination
        Page<Variant> variantsPage = variantRepository.findByProductId(productId, pageable);

        // Convert the page of entities to a page of DTOs
        return variantsPage.map(variantMapper::toDto);
    }

    @Override
    public Page<VariantDTO> getAllProductVariants(Pageable pageable) {
        // Retrieve all variants with pagination
        Page<Variant> variantsPage = variantRepository.findAll(pageable);

        // Convert the page of entities to a page of DTOs
        return variantsPage.map(variantMapper::toDto);
    }

    @Override
    public VariantDTO updateProductVariant(Long id, VariantDTO variantDTO) {
        // Retrieve the existing variant by ID
        Variant existingVariant = variantRepository.findById(id)
                .orElseThrow(() -> new VariantNotFoundException("Variant not found with ID: " + id));

        // Update the existing variant with values from the DTO
        variantMapper.updateEntity(variantDTO, existingVariant);

        // Save the updated variant to the repository
        Variant updatedVariant = variantRepository.save(existingVariant);

        // Convert the updated entity back to DTO
        return variantMapper.toDto(updatedVariant);
    }

    @Override
    public void deleteProductVariant(Long id) {
        // Retrieve the existing variant by ID
        Variant existingVariant = variantRepository.findById(id)
                .orElseThrow(() -> new VariantNotFoundException("Variant not found with ID: " + id));

        // Delete the variant from the repository
        variantRepository.delete(existingVariant);
    }
}

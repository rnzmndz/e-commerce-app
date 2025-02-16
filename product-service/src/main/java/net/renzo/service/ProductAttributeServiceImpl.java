package net.renzo.service;

import net.renzo.dto.ProductAttributeDTO;
import net.renzo.exception.ProductAttributeNotFoundException;
import net.renzo.mapper.ProductAttributeMapper;
import net.renzo.model.ProductAttribute;
import net.renzo.repository.ProductAttributeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService{

    private final ProductAttributeRepository productAttributeRepository;
    private final ProductAttributeMapper productAttributeMapper;

    public ProductAttributeServiceImpl(ProductAttributeRepository productAttributeRepository, ProductAttributeMapper productAttributeMapper) {
        this.productAttributeRepository = productAttributeRepository;
        this.productAttributeMapper = productAttributeMapper;
    }


    @Override
    @Transactional
    public ProductAttributeDTO save(ProductAttributeDTO attribute) {
        // Convert the ProductAttributeDTO to a ProductAttribute entity
        ProductAttribute productAttributeEntity = productAttributeMapper.toEntity(attribute);

        // Save the ProductAttribute entity to the repository
        productAttributeEntity = productAttributeRepository.save(productAttributeEntity);

        // Convert the saved ProductAttribute entity back to a ProductAttributeDTO
        return productAttributeMapper.toDto(productAttributeEntity);
    }

    @Override
    public Optional<ProductAttributeDTO> findById(Long id) {
        // Find the product attribute by ID, throw exception if not found
        ProductAttribute productAttribute = productAttributeRepository.findById(id)
                .orElseThrow(() -> new ProductAttributeNotFoundException("Product attribute not found"));

        // Convert the found ProductAttribute entity to ProductAttributeDTO and return it wrapped in an Optional
        return Optional.of(productAttributeMapper.toDto(productAttribute));
    }

    @Override
    public Page<ProductAttributeDTO> findAll(Pageable pageable) {
        // Retrieve all ProductAttribute entities with pagination
        return productAttributeRepository.findAll(pageable)
                // Convert each ProductAttribute entity to a ProductAttributeDTO
                .map(productAttributeMapper::toDto);
    }

    @Override
    @Transactional
    public ProductAttributeDTO update(ProductAttributeDTO attribute) {
        // Find the existing product attribute by ID, throw exception if not found
        ProductAttribute existingProductAttribute = productAttributeRepository.findById(attribute.getId())
                .orElseThrow(() -> new ProductAttributeNotFoundException("Product attribute not found"));

        // Update the existing product attribute with the new values
        productAttributeMapper.updateEntity(attribute, existingProductAttribute);

        // Save the updated product attribute entity to the repository
        existingProductAttribute = productAttributeRepository.save(existingProductAttribute);

        // Convert the updated product attribute entity back to a ProductAttributeDTO
        return productAttributeMapper.toDto(existingProductAttribute);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // Find the existing product attribute by ID, throw exception if not found
        ProductAttribute existingProductAttribute = productAttributeRepository.findById(id)
                .orElseThrow(() -> new ProductAttributeNotFoundException("Product attribute not found"));

        // Delete the product attribute from the repository
        productAttributeRepository.delete(existingProductAttribute);
    }
}

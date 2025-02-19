package net.renzo.service;

import net.renzo.dto.AttributeDTO;
import net.renzo.exception.ProductAttributeNotFoundException;
import net.renzo.exception.ProductNotFoundException;
import net.renzo.mapper.ProductAttributeMapper;
import net.renzo.model.Attribute;
import net.renzo.model.Product;
import net.renzo.repository.AttributeRepository;
import net.renzo.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;
    private final ProductRepository productRepository;
    private final ProductAttributeMapper productAttributeMapper;

    public AttributeServiceImpl(AttributeRepository attributeRepository, ProductAttributeMapper productAttributeMapper
            , ProductRepository productRepository) {
        this.attributeRepository = attributeRepository;
        this.productAttributeMapper = productAttributeMapper;
        this.productRepository = productRepository;
    }


    @Override
    @Transactional
    public AttributeDTO save(AttributeDTO attribute) {
        // Convert the ProductAttributeDTO to a ProductAttribute entity
        Attribute attributeEntity = productAttributeMapper.toEntity(attribute);

        // Save the ProductAttribute entity to the repository
        attributeEntity = attributeRepository.save(attributeEntity);

        // Convert the saved ProductAttribute entity back to a ProductAttributeDTO
        return productAttributeMapper.toDto(attributeEntity);
    }

    @Override
    public Optional<AttributeDTO> findById(Long id) {
        // Find the product attribute by ID, throw exception if not found
        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new ProductAttributeNotFoundException("Product attribute not found"));

        // Convert the found ProductAttribute entity to ProductAttributeDTO and return it wrapped in an Optional
        return Optional.of(productAttributeMapper.toDto(attribute));
    }

    @Override
    public Page<AttributeDTO> findAll(Pageable pageable) {
        // Retrieve all ProductAttribute entities with pagination
        return attributeRepository.findAll(pageable)
                // Convert each ProductAttribute entity to a ProductAttributeDTO
                .map(productAttributeMapper::toDto);
    }

    @Override
    @Transactional
    public AttributeDTO update(AttributeDTO attribute) {
        // Find the existing product attribute by ID, throw exception if not found
        Attribute existingAttribute = attributeRepository.findById(attribute.getId())
                .orElseThrow(() -> new ProductAttributeNotFoundException("Product attribute not found"));

        // Update the existing product attribute with the new values
        productAttributeMapper.updateEntity(attribute, existingAttribute);

        // Save the updated product attribute entity to the repository
        existingAttribute = attributeRepository.save(existingAttribute);

        // Convert the updated product attribute entity back to a ProductAttributeDTO
        return productAttributeMapper.toDto(existingAttribute);
    }

    @Override
    public void addAttributeToProduct(Long productId, Long attributeId) {
        // Find the attribute by ID, throw exception if not found
        Attribute attribute = attributeRepository.findById(attributeId)
                .orElseThrow(() -> new ProductAttributeNotFoundException("Attribute not found"));

        // Find the product by ID, throw exception if not found
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        // Add the attribute to the product
        attribute.addProduct(product);

        // Save the updated attribute
        attributeRepository.save(attribute);
    }

    @Override
    public void removeAttributeFromProduct(Long productId, Long attributeId) {
        // Find the attribute by ID, throw exception if not found
        Attribute attribute = attributeRepository.findById(attributeId)
                .orElseThrow(() -> new ProductAttributeNotFoundException("Attribute not found"));

        // Find the product by ID, throw exception if not found
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        // Remove the attribute from the product
        attribute.removeProduct(product);

        // Save the updated attribute
        attributeRepository.save(attribute);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // Find the existing product attribute by ID, throw exception if not found
        Attribute existingAttribute = attributeRepository.findById(id)
                .orElseThrow(() -> new ProductAttributeNotFoundException("Product attribute not found"));

        // Delete the product attribute from the repository
        attributeRepository.delete(existingAttribute);
    }
}

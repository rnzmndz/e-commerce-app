package net.renzo.service;

import net.renzo.dto.AttributeDTO;
import net.renzo.exception.ProductAttributeNotFoundException;
import net.renzo.mapper.ProductAttributeMapper;
import net.renzo.model.Attribute;
import net.renzo.repository.AttributeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;
    private final ProductAttributeMapper productAttributeMapper;

    public AttributeServiceImpl(AttributeRepository attributeRepository, ProductAttributeMapper productAttributeMapper) {
        this.attributeRepository = attributeRepository;
        this.productAttributeMapper = productAttributeMapper;
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
    @Transactional
    public void deleteById(Long id) {
        // Find the existing product attribute by ID, throw exception if not found
        Attribute existingAttribute = attributeRepository.findById(id)
                .orElseThrow(() -> new ProductAttributeNotFoundException("Product attribute not found"));

        // Delete the product attribute from the repository
        attributeRepository.delete(existingAttribute);
    }
}

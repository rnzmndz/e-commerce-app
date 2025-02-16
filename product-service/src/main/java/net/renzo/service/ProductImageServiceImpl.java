package net.renzo.service;

import net.renzo.dto.ProductImageDTO;
import net.renzo.exception.ProductImageNotFoundException;
import net.renzo.mapper.ProductImageMapper;
import net.renzo.model.ProductImage;
import net.renzo.repository.ProductImageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductImageServiceImpl implements ProductImageService{

    private final ProductImageRepository productImageRepository;
    private final ProductImageMapper productImageMapper;

    public ProductImageServiceImpl(ProductImageRepository productImageRepository, ProductImageMapper productImageMapper) {
        this.productImageRepository = productImageRepository;
        this.productImageMapper = productImageMapper;
    }
    @Override
    @Transactional
    public ProductImageDTO save(ProductImageDTO productImageDTO) {
        // Convert ProductImageDTO to ProductImage entity
        ProductImage productImage = productImageMapper.toEntity(productImageDTO);

        // Save the ProductImage entity to the repository
        productImage = productImageRepository.save(productImage);

        // Convert the saved ProductImage entity back to ProductImageDTO
        return productImageMapper.toDto(productImage);
    }

    @Override
    public Optional<ProductImageDTO> findById(Long id) {
        // Retrieve the ProductImage entity by id, throw exception if not found
        ProductImage productImage = productImageRepository.findById(id)
                .orElseThrow(() -> new ProductImageNotFoundException("Product image not found"));

        // Convert the ProductImage entity to ProductImageDTO and return it wrapped in an Optional
        return Optional.of(productImageMapper.toDto(productImage));
    }

    @Override
    public Page<ProductImageDTO> findAll(Pageable pageable) {
        // Retrieve all ProductImage entities from the repository
        Page<ProductImage> productImages = productImageRepository.findAll(pageable);

        // Convert the list of ProductImage entities to a list of ProductImageDTOs
        return productImages.map(productImageMapper::toDto);
    }

    @Override
    @Transactional
    public ProductImageDTO update(ProductImageDTO productImageDTO) {
        // Retrieve the existing ProductImage entity by id, throw exception if not found
        ProductImage existingProductImage = productImageRepository.findById(productImageDTO.getId())
                .orElseThrow(() -> new ProductImageNotFoundException("Product image not found"));

        // Update the existing ProductImage entity with values from the DTO
        productImageMapper.updateEntity(productImageDTO, existingProductImage);

        // Save the updated ProductImage entity to the repository
        existingProductImage = productImageRepository.save(existingProductImage);

        // Convert the updated ProductImage entity back to ProductImageDTO
        return productImageMapper.toDto(existingProductImage);
    }

    @Override
    public void deleteById(Long id) {
        // Retrieve the existing ProductImage entity by id, throw exception if not found
        ProductImage existingProductImage = productImageRepository.findById(id)
                .orElseThrow(() -> new ProductImageNotFoundException("Product image not found"));

        // Delete the ProductImage entity from the repository
        productImageRepository.delete(existingProductImage);
    }
}

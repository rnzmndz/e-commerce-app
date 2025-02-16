package net.renzo.service;

import net.renzo.dto.ProductReviewDTO;
import net.renzo.exception.ProductReviewNotFoundException;
import net.renzo.mapper.ProductReviewMapper;
import net.renzo.model.ProductReview;
import net.renzo.repository.ProductReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductReviewServiceImpl implements ProductReviewService{

    private final ProductReviewRepository productReviewRepository;
    private final ProductReviewMapper productReviewMapper;

    public ProductReviewServiceImpl(ProductReviewRepository productReviewRepository, ProductReviewMapper productReviewMapper) {
        this.productReviewRepository = productReviewRepository;
        this.productReviewMapper = productReviewMapper;
    }

    @Override
    @Transactional
    public ProductReviewDTO createProductReview(ProductReviewDTO productReview) {
        // Convert ProductReviewDTO to ProductReview entity
        ProductReview productReviewEntity = productReviewMapper.toEntity(productReview);

        // Save the ProductReview entity to the repository
        productReviewEntity = productReviewRepository.save(productReviewEntity);

        // Convert the saved ProductReview entity back to ProductReviewDTO
        return productReviewMapper.toDto(productReviewEntity);
    }

    @Override
    public ProductReviewDTO getProductReviewByProductId(Long id) {
        // Retrieve the ProductReview entity by id, throw exception if not found
        ProductReview productReview = productReviewRepository.findById(id)
                .orElseThrow(() -> new ProductReviewNotFoundException("Product review not found"));

        // Convert the ProductReview entity to ProductReviewDTO and return it
        return productReviewMapper.toDto(productReview);
    }

    @Override
    public Page<ProductReviewDTO> getProductReviewByProductId(Long id, Pageable pageable) {
        // Retrieve the ProductReview entities by productId
        Page<ProductReview> productReviews = productReviewRepository.findByProductId(id, pageable);
        if (productReviews.isEmpty()) {
            throw new ProductReviewNotFoundException("Product reviews not found for product id: " + id);
        }

        // Convert the ProductReview entities to ProductReviewDTOs
        return productReviews.map(productReviewMapper::toDto);
    }
    @Override
    @Transactional
    public ProductReviewDTO updateProductReview(Long id, ProductReviewDTO productReview) {
        // Retrieve the existing ProductReview entity by id, throw exception if not found
        ProductReview existingProductReview = productReviewRepository.findById(id)
                .orElseThrow(() -> new ProductReviewNotFoundException("Product review not found"));

        // Update the existing ProductReview entity with values from the provided ProductReviewDTO
        productReviewMapper.updateEntity(productReview, existingProductReview);

        // Save the updated ProductReview entity to the repository
        ProductReview updatedProductReview = productReviewRepository.save(existingProductReview);

        // Convert the updated ProductReview entity back to ProductReviewDTO and return it
        return productReviewMapper.toDto(updatedProductReview);
    }

    @Override
    @Transactional
    public void deleteProductReview(Long id) {
        // Retrieve the existing ProductReview entity by id, throw exception if not found
        ProductReview existingProductReview = productReviewRepository.findById(id)
                .orElseThrow(() -> new ProductReviewNotFoundException("Product review not found"));

        // Delete the ProductReview entity from the repository
        productReviewRepository.delete(existingProductReview);
    }
}

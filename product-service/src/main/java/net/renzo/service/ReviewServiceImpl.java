package net.renzo.service;

import net.renzo.dto.ReviewDTO;
import net.renzo.exception.ProductReviewNotFoundException;
import net.renzo.mapper.ProductReviewMapper;
import net.renzo.model.Review;
import net.renzo.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductReviewMapper productReviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductReviewMapper productReviewMapper) {
        this.reviewRepository = reviewRepository;
        this.productReviewMapper = productReviewMapper;
    }

    @Override
    @Transactional
    public ReviewDTO createProductReview(ReviewDTO productReview) {
        // Convert ProductReviewDTO to ProductReview entity
        Review reviewEntity = productReviewMapper.toEntity(productReview);

        // Save the ProductReview entity to the repository
        reviewEntity = reviewRepository.save(reviewEntity);

        // Convert the saved ProductReview entity back to ProductReviewDTO
        return productReviewMapper.toDto(reviewEntity);
    }

    @Override
    public ReviewDTO getProductReviewByProductId(Long id) {
        // Retrieve the ProductReview entity by id, throw exception if not found
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ProductReviewNotFoundException("Product review not found"));

        // Convert the ProductReview entity to ProductReviewDTO and return it
        return productReviewMapper.toDto(review);
    }

    @Override
    public Page<ReviewDTO> getProductReviewByProductId(Long id, Pageable pageable) {
        // Retrieve the ProductReview entities by productId
        Page<Review> productReviews = reviewRepository.findByProductId(id, pageable);
        if (productReviews.isEmpty()) {
            throw new ProductReviewNotFoundException("Product reviews not found for product id: " + id);
        }

        // Convert the ProductReview entities to ProductReviewDTOs
        return productReviews.map(productReviewMapper::toDto);
    }
    @Override
    @Transactional
    public ReviewDTO updateProductReview(Long id, ReviewDTO productReview) {
        // Retrieve the existing ProductReview entity by id, throw exception if not found
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ProductReviewNotFoundException("Product review not found"));

        // Update the existing ProductReview entity with values from the provided ProductReviewDTO
        productReviewMapper.updateEntity(productReview, existingReview);

        // Save the updated ProductReview entity to the repository
        Review updatedReview = reviewRepository.save(existingReview);

        // Convert the updated ProductReview entity back to ProductReviewDTO and return it
        return productReviewMapper.toDto(updatedReview);
    }

    @Override
    @Transactional
    public void deleteProductReview(Long id) {
        // Retrieve the existing ProductReview entity by id, throw exception if not found
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ProductReviewNotFoundException("Product review not found"));

        // Delete the ProductReview entity from the repository
        reviewRepository.delete(existingReview);
    }
}

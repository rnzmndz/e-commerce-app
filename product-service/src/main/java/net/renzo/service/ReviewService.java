package net.renzo.service;

import net.renzo.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service interface for managing product reviews.
 */
public interface ReviewService {

    /**
     * Creates a new product review.
     *
     * @param productReview the product review to create
     * @return the created product review
     */
    ReviewDTO createProductReview(ReviewDTO productReview);

    /**
     * Retrieves a product review by its ID.
     *
     * @param id the ID of the product review
     * @return an Optional containing the product review if found, or empty if not found
     */
    Optional<ReviewDTO> getProductReviewById(Long id);

    /**
     * Retrieves all product reviews with pagination.
     *
     * @param pageable the pagination information
     * @return a page of product reviews
     */
    Page<ReviewDTO> getAllProductReview(Pageable pageable);

    /**
     * Retrieves a product review by the product ID.
     *
     * @param productId the ID of the product
     * @return the product review for the specified product
     */
    Page<ReviewDTO> getProductReviewByProductId(Long productId, Pageable pageable);

    /**
     * Updates an existing product review.
     *
     * @param id the ID of the product review to update
     * @param productReview the updated product review
     * @return the updated product review
     */
    ReviewDTO updateProductReview(Long id, ReviewDTO productReview);

    /**
     * Deletes a product review by its ID.
     *
     * @param id the ID of the product review to delete
     */
    void deleteProductReview(Long id);
}
package net.renzo.service;

import net.renzo.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @return the product review with the specified ID
     */
    Page<ReviewDTO> getProductReviewByProductId(Long id, Pageable pageable);

    /**
     * Retrieves a product review by the product ID.
     *
     * @param productId the ID of the product
     * @return the product review for the specified product
     */
    ReviewDTO getProductReviewByProductId(Long productId);

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
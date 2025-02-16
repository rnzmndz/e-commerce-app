package net.renzo.service;

import net.renzo.dto.ProductReviewDTO;
import net.renzo.model.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing product reviews.
 */
public interface ProductReviewService {

    /**
     * Creates a new product review.
     *
     * @param productReview the product review to create
     * @return the created product review
     */
    ProductReviewDTO createProductReview(ProductReviewDTO productReview);

    /**
     * Retrieves a product review by its ID.
     *
     * @param id the ID of the product review
     * @return the product review with the specified ID
     */
    Page<ProductReviewDTO> getProductReviewByProductId(Long id, Pageable pageable);

    /**
     * Retrieves a product review by the product ID.
     *
     * @param productId the ID of the product
     * @return the product review for the specified product
     */
    ProductReviewDTO getProductReviewByProductId(Long productId);

    /**
     * Updates an existing product review.
     *
     * @param id the ID of the product review to update
     * @param productReview the updated product review
     * @return the updated product review
     */
    ProductReviewDTO updateProductReview(Long id, ProductReviewDTO productReview);

    /**
     * Deletes a product review by its ID.
     *
     * @param id the ID of the product review to delete
     */
    void deleteProductReview(Long id);
}
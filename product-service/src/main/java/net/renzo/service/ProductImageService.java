package net.renzo.service;

import net.renzo.dto.ProductImageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductImageService {

    /**
     * Saves a given product image.
     *
     * @param productImage the product image to save
     * @return the saved product image
     */
    ProductImageDTO save(ProductImageDTO productImage);

    /**
     * Finds a product image by its ID.
     *
     * @param id the ID of the product image
     * @return an Optional containing the found product image, or empty if not found
     */
    Optional<ProductImageDTO> findById(Long id);

    /**
     * Finds all product images.
     *
     * @return a list of all product images
     */
    Page<ProductImageDTO> findAll(Pageable pageable);

    /**
     * Updates a given product image.
     *
     * @param productImageDTO the product image to update
     * @return the updated product image
     */
    ProductImageDTO update(ProductImageDTO productImageDTO);

    /**
     * Deletes a product image by its ID.
     *
     * @param id the ID of the product image to delete
     */
    void deleteById(Long id);
}
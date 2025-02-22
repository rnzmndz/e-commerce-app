package net.renzo.service;

import net.renzo.dto.VariantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service interface for managing product variants.
 */
public interface VariantService {

    /**
     * Creates a new product variant.
     *
     * @param variantDTO the product variant data transfer object
     * @return the created product variant data transfer object
     */
    VariantDTO createProductVariant(VariantDTO variantDTO);

    /**
     * Retrieves a product variant by its ID.
     *
     * @param id the ID of the product variant
     * @return the product variant data transfer object
     */
    Optional<VariantDTO> getProductVariantById(Long id);

    /**
     * Retrieves product variants by product ID with pagination.
     *
     * @param productId the ID of the product
     * @param pageable  the pagination information
     * @return a page of product variant data transfer objects
     */
    Page<VariantDTO> getProductVariantsByProductId(Long productId, Pageable pageable);

    /**
     * Retrieves all product variants with pagination.
     *
     * @param pageable the pagination information
     * @return a page of product variant data transfer objects
     */
    Page<VariantDTO> getAllProductVariants(Pageable pageable);

    /**
     * Updates an existing product variant.
     *
     * @param id                the ID of the product variant to update
     * @param variantDTO the updated product variant data transfer object
     * @return the updated product variant data transfer object
     */
    VariantDTO updateProductVariant(Long id, VariantDTO variantDTO);

    /**
     * Deletes a product variant by its ID.
     *
     * @param id the ID of the product variant to delete
     */
    void deleteProductVariant(Long id);
}
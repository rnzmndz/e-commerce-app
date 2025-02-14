package net.renzo.service;

import net.renzo.dto.ProductVariantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing product variants.
 */
public interface ProductVariantService {

    /**
     * Creates a new product variant.
     *
     * @param productVariantDTO the product variant data transfer object
     * @return the created product variant data transfer object
     */
    ProductVariantDTO createProductVariant(ProductVariantDTO productVariantDTO);

    /**
     * Retrieves a product variant by its ID.
     *
     * @param id the ID of the product variant
     * @return the product variant data transfer object
     */
    ProductVariantDTO getProductVariantById(Long id);

    /**
     * Retrieves product variants by product ID with pagination.
     *
     * @param productId the ID of the product
     * @param pageable  the pagination information
     * @return a page of product variant data transfer objects
     */
    Page<ProductVariantDTO> getProductVariantsByProductId(Long productId, Pageable pageable);

    /**
     * Retrieves all product variants with pagination.
     *
     * @param pageable the pagination information
     * @return a page of product variant data transfer objects
     */
    Page<ProductVariantDTO> getAllProductVariants(Pageable pageable);

    /**
     * Updates an existing product variant.
     *
     * @param id                the ID of the product variant to update
     * @param productVariantDTO the updated product variant data transfer object
     * @return the updated product variant data transfer object
     */
    ProductVariantDTO updateProductVariant(Long id, ProductVariantDTO productVariantDTO);

    /**
     * Deletes a product variant by its ID.
     *
     * @param id the ID of the product variant to delete
     */
    void deleteProductVariant(Long id);
}
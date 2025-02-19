package net.renzo.service;

import net.renzo.dto.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service for managing brands.
 */
public interface BrandService {

    /**
     * Create a new brand.
     * @param brand the brand to create
     * @return the created brand
     */
    BrandDTO createBrand(BrandDTO brand);

    /**
     * Get a brand by its ID.
     * @param id the ID of the brand
     * @return the brand with the given ID
     */
    Optional<BrandDTO> findBrandById(Long id);

    /**
     * Get a brand by its name.
     * @param name the name of the brand
     * @return the brand with the given name
     */
    Optional<BrandDTO> findBrandByName(String name);

    /**
     * Get all brands.
     * @return a list of all brands
     */
    Page<BrandDTO> findAllBrands(Pageable pageable);

    /**
     * Update an existing brand.
     * @param id the ID of the brand to update
     * @param brand the updated brand
     * @return the updated brand
     */
    BrandDTO updateBrand(Long id, BrandDTO brand);

    /**
     * Add a product to a brand.
     * @param brandId the ID of the brand
     * @param productId the ID of the product
     */
    void addProductToBrand(Long brandId, Long productId);

    /**
     * Remove a product from a brand.
     * @param brandId the ID of the brand
     * @param productId the ID of the product
     */
    void removeProductFromBrand(Long brandId, Long productId);

    /**
     * Delete a brand by its ID.
     * @param id the ID of the brand to delete
     */
    void deleteBrand(Long id);
}
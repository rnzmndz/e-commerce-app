package net.renzo.service;

import net.renzo.dto.ImageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ImageService {

    /**
     * Saves a given product image.
     *
     * @param productImage the product image to save
     * @return the saved product image
     */
    ImageDTO save(ImageDTO productImage);

    /**
     * Finds a product image by its ID.
     *
     * @param id the ID of the product image
     * @return an Optional containing the found product image, or empty if not found
     */
    Optional<ImageDTO> findById(Long id);

    /**
     * Finds all product images.
     *
     * @return a list of all product images
     */
    Page<ImageDTO> findAll(Pageable pageable);

    /**
     * Updates a given product image.
     *
     * @param imageDTO the product image to update
     * @return the updated product image
     */
    ImageDTO update(Long id, ImageDTO imageDTO);

    /**
     * Deletes a product image by its ID.
     *
     * @param id the ID of the product image to delete
     */
    void deleteById(Long id);
}
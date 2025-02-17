package net.renzo.service;

import net.renzo.dto.AttributeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AttributeService {

    /**
     * Saves a given product attribute.
     *
     * @param attribute the attribute to save
     * @return the saved attribute
     */
    AttributeDTO save(AttributeDTO attribute);

    /**
     * Finds a product attribute by its ID.
     *
     * @param id the ID of the attribute
     * @return an Optional containing the found attribute, or empty if not found
     */
    Optional<AttributeDTO> findById(Long id);

    /**
     * Finds all product attributes.
     *
     * @return a list of all product attributes
     */
    Page<AttributeDTO> findAll(Pageable pageable);

    /**
     * Updates a given product attribute.
     *
     * @param attribute the attribute to update
     * @return the updated attribute
     */
    AttributeDTO update(AttributeDTO attribute);

    /**
     * Deletes a product attribute by its ID.
     *
     * @param id the ID of the attribute to delete
     */
    void deleteById(Long id);
}
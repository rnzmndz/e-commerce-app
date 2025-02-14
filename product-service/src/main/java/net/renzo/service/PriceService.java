package net.renzo.service;

import net.renzo.dto.PriceDTO;
import net.renzo.model.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing prices.
 */
public interface PriceService {

    /**
     * Saves a given price.
     *
     * @param price the price to save
     * @return the saved price
     */
    PriceDTO save(PriceDTO price);

    /**
     * Finds a price by its ID.
     *
     * @param id the ID of the price
     * @return an Optional containing the found price, or empty if not found
     */
    Optional<PriceDTO> findById(Long id);

    /**
     * Finds all prices.
     *
     * @return a list of all prices
     */
    Page<PriceDTO> findAll(Pageable pageable);

    /**
     * Updates a given price.
     *
     * @param price the price to update
     * @return the updated price
     */
    PriceDTO update(PriceDTO price);

    /**
     * Deletes a price by its ID.
     *
     * @param id the ID of the price to delete
     */
    void deleteById(Long id);
}
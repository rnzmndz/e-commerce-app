package net.renzo.service;

import net.renzo.dto.PriceDTO;
import net.renzo.exception.PriceNotFoundException;
import net.renzo.mapper.PriceMapper;
import net.renzo.model.Price;
import net.renzo.repository.PriceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService{

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    @Transactional
    public PriceDTO save(PriceDTO price) {
        // Convert the PriceDTO to a Price entity
        Price priceEntity = priceMapper.toEntity(price);

        // Save the Price entity to the repository
        priceEntity = priceRepository.save(priceEntity);

        // Convert the saved Price entity back to a PriceDTO
        PriceDTO result = priceMapper.toDto(priceEntity);

        // Return the saved PriceDTO
        return result;
    }

    @Override
    public Optional<PriceDTO> findById(Long id) {
        // Find the price by ID, throw exception if not found
        Price price = priceRepository.findById(id)
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));

        // Convert the found Price entity to PriceDTO and return it wrapped in an Optional
        return Optional.of(priceMapper.toDto(price));
    }

    @Override
    public Page<PriceDTO> findAll(Pageable pageable) {
        // Retrieve all prices from the repository with pagination
        return priceRepository.findAll(pageable)
                // Convert each Price entity to a PriceDTO
                .map(priceMapper::toDto);
    }

    @Override
    @Transactional
    public PriceDTO update(PriceDTO price) {
        // Find the existing price by ID, throw exception if not found
        Price existingPrice = priceRepository.findById(price.getId())
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));

        // Update the existing price entity with the new data
        priceMapper.updateEntity(price, existingPrice);

        // Save the updated price entity to the repository
        existingPrice = priceRepository.save(existingPrice);

        // Convert the updated price entity back to a PriceDTO
        return priceMapper.toDto(existingPrice);
    }

    @Override
    public void deleteById(Long id) {
        // Find the existing price by ID, throw exception if not found
        Price existingPrice = priceRepository.findById(id)
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));

        // Delete the price entity from the repository
        priceRepository.delete(existingPrice);
    }
}

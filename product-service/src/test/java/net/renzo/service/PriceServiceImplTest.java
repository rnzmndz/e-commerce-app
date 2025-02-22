package net.renzo.service;

import net.renzo.dto.PriceDTO;
import net.renzo.mapper.PriceMapper;
import net.renzo.model.Price;
import net.renzo.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void save() {
        PriceDTO priceDTO = new PriceDTO();
        Price price = new Price();
        Price savedPrice = new Price();
        PriceDTO savedPriceDTO = new PriceDTO();

        when(priceMapper.toEntity(priceDTO)).thenReturn(price);
        when(priceRepository.save(price)).thenReturn(savedPrice);
        when(priceMapper.toDto(savedPrice)).thenReturn(savedPriceDTO);

        PriceDTO result = priceService.save(priceDTO);

        assertEquals(savedPriceDTO, result);
        verify(priceMapper).toEntity(priceDTO);
        verify(priceRepository).save(price);
        verify(priceMapper).toDto(savedPrice);
    }

    @Test
    void findById() {
        Long id = 1L;
        Price price = new Price();
        PriceDTO priceDTO = new PriceDTO();

        when(priceRepository.findById(id)).thenReturn(Optional.of(price));
        when(priceMapper.toDto(price)).thenReturn(priceDTO);

        Optional<PriceDTO> result = priceService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(priceDTO, result.get());
        verify(priceRepository).findById(id);
        verify(priceMapper).toDto(price);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Price> pricePage = new PageImpl<>(Collections.singletonList(new Price()));
        PriceDTO priceDTO = new PriceDTO();

        when(priceRepository.findAll(pageable)).thenReturn(pricePage);
        when(priceMapper.toDto(any(Price.class))).thenReturn(priceDTO);

        Page<PriceDTO> result = priceService.findAll(pageable);

        assertEquals(1, result.getTotalElements());
        verify(priceRepository).findAll(pageable);
        verify(priceMapper).toDto(any(Price.class));
    }

    @Test
    void update() {
        PriceDTO priceDTO = new PriceDTO();
        Price existingPrice = new Price();
        Price updatedPrice = new Price();
        PriceDTO updatedPriceDTO = new PriceDTO();

        when(priceRepository.findById(priceDTO.getId())).thenReturn(Optional.of(existingPrice));
        doNothing().when(priceMapper).updateEntity(priceDTO, existingPrice);
        when(priceRepository.save(existingPrice)).thenReturn(updatedPrice);
        when(priceMapper.toDto(updatedPrice)).thenReturn(updatedPriceDTO);

        PriceDTO result = priceService.update(priceDTO);

        assertEquals(updatedPriceDTO, result);
        verify(priceRepository).findById(priceDTO.getId());
        verify(priceMapper).updateEntity(priceDTO, existingPrice);
        verify(priceRepository).save(existingPrice);
        verify(priceMapper).toDto(updatedPrice);
    }

    @Test
    void deleteById() {
        Long id = 1L;
        Price price = new Price();

        when(priceRepository.findById(id)).thenReturn(Optional.of(price));
        doNothing().when(priceRepository).delete(price);

        priceService.deleteById(id);

        verify(priceRepository).findById(id);
        verify(priceRepository).delete(price);
    }
}
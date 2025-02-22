package net.renzo.service;

import net.renzo.dto.VariantDTO;
import net.renzo.mapper.VariantMapper;
import net.renzo.model.Product;
import net.renzo.model.Variant;
import net.renzo.repository.ProductRepository;
import net.renzo.repository.VariantRepository;
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

class VariantServiceImplTest {

    @Mock
    private VariantRepository variantRepository;

    @Mock
    private VariantMapper variantMapper;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private VariantServiceImpl variantService;

    @Test
    void createProductVariant() {
        VariantDTO variantDTO = new VariantDTO();
        Variant variant = new Variant();
        Variant savedVariant = new Variant();

        when(variantMapper.toEntity(variantDTO)).thenReturn(variant);
        when(variantRepository.save(variant)).thenReturn(savedVariant);
        when(variantMapper.toDto(savedVariant)).thenReturn(variantDTO);

        VariantDTO result = variantService.createProductVariant(variantDTO);

        assertEquals(variantDTO, result);
        verify(variantMapper).toEntity(variantDTO);
        verify(variantRepository).save(variant);
        verify(variantMapper).toDto(savedVariant);
    }

    @Test
    void getProductVariantById() {
        Long id = 1L;
        Variant variant = new Variant();
        VariantDTO variantDTO = new VariantDTO();

        when(variantRepository.findById(id)).thenReturn(Optional.of(variant));
        when(variantMapper.toDto(variant)).thenReturn(variantDTO);

        Optional<VariantDTO> result = variantService.getProductVariantById(id);

        assertEquals(variantDTO, result.get());
        verify(variantRepository).findById(id);
        verify(variantMapper).toDto(variant);
    }

    @Test
    void getProductVariantsByProductId() {
        Long productId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Page<Variant> variantsPage = new PageImpl<>(Collections.emptyList());

        lenient().when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));
        lenient().when(variantRepository.findByProductId(productId, pageable)).thenReturn(variantsPage);
        lenient().when(variantMapper.toDto(any(Variant.class))).thenReturn(new VariantDTO());

        Page<VariantDTO> result = variantService.getProductVariantsByProductId(productId, pageable);

        assertNotNull(result);
        verify(productRepository).findById(productId);
        verify(variantRepository).findByProductId(productId, pageable);
    }

    @Test
    void getAllProductVariants() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Variant> variantsPage = new PageImpl<>(Collections.emptyList());

        lenient().when(variantRepository.findAll(pageable)).thenReturn(variantsPage);
        lenient().when(variantMapper.toDto(any(Variant.class))).thenReturn(new VariantDTO());

        Page<VariantDTO> result = variantService.getAllProductVariants(pageable);

        assertNotNull(result);
        verify(variantRepository).findAll(pageable);
    }

    @Test
    void updateProductVariant() {
        Long id = 1L;
        VariantDTO variantDTO = new VariantDTO();
        Variant existingVariant = new Variant();
        Variant updatedVariant = new Variant();

        when(variantRepository.findById(id)).thenReturn(Optional.of(existingVariant));
        doNothing().when(variantMapper).updateEntity(variantDTO, existingVariant);
        when(variantRepository.save(existingVariant)).thenReturn(updatedVariant);
        when(variantMapper.toDto(updatedVariant)).thenReturn(variantDTO);

        VariantDTO result = variantService.updateProductVariant(id, variantDTO);

        assertEquals(variantDTO, result);
        verify(variantRepository).findById(id);
        verify(variantMapper).updateEntity(variantDTO, existingVariant);
        verify(variantRepository).save(existingVariant);
        verify(variantMapper).toDto(updatedVariant);
    }

    @Test
    void deleteProductVariant() {
        Long id = 1L;
        Variant existingVariant = new Variant();

        when(variantRepository.findById(id)).thenReturn(Optional.of(existingVariant));
        doNothing().when(variantRepository).delete(existingVariant);

        variantService.deleteProductVariant(id);

        verify(variantRepository).findById(id);
        verify(variantRepository).delete(existingVariant);
    }
}
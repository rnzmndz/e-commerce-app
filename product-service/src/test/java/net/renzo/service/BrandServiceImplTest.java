package net.renzo.service;

import net.renzo.dto.BrandDTO;
import net.renzo.mapper.BrandMapper;
import net.renzo.model.Brand;
import net.renzo.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    @InjectMocks
    private BrandServiceImpl brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // TODO Fix the testCreateBrand test case
    @Test
    void testCreateBrand() {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName("Test Brand");

        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Test Brand");

        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
        when(brandMapper.toDTO(brand)).thenReturn(BrandDTO.builder().id(1L).name("Test Brand").build());

        BrandDTO result = brandService.createBrand(brandDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Brand", result.getName());

        verify(brandRepository, times(1)).save(any(Brand.class));
    }
    @Test
    void testFindBrandById() {
        Long brandId = 1L;
        Brand brand = new Brand();
        brand.setId(brandId);
        brand.setName("Test Brand");

        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));
        when(brandMapper.toDTO(brand)).thenReturn(BrandDTO.builder().id(brandId).name("Test Brand").build());

        Optional<BrandDTO> result = brandService.findBrandById(brandId);

        assertTrue(result.isPresent());
        assertEquals(brand.getId(), result.get().getId());
        assertEquals(brand.getName(), result.get().getName());

        verify(brandRepository, times(1)).findById(brandId);
        verify(brandMapper, times(1)).toDTO(brand);
    }
    @Test
    void testFindBrandByName() {
        String brandName = "Test Brand";
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName(brandName);

        when(brandRepository.findByName(brandName)).thenReturn(Optional.of(brand));
        when(brandMapper.toDTO(brand)).thenReturn(BrandDTO.builder().id(1L).name(brandName).build());

        Optional<BrandDTO> result = brandService.findBrandByName(brandName);

        assertTrue(result.isPresent());
        assertEquals(brand.getId(), result.get().getId());
        assertEquals(brand.getName(), result.get().getName());

        verify(brandRepository, times(1)).findByName(brandName);
    }
    @Test
    void testUpdateBrand() {
        Long brandId = 1L;
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(brandId);
        brandDTO.setName("Updated Brand");

        Brand existingBrand = new Brand();
        existingBrand.setId(brandId);
        existingBrand.setName("Old Brand");

        when(brandRepository.findById(brandId)).thenReturn(Optional.of(existingBrand));
        when(brandRepository.save(any(Brand.class))).thenReturn(existingBrand);
        when(brandMapper.toDTO(existingBrand)).thenReturn(BrandDTO.builder().id(brandId).name("Updated Brand").build());

        BrandDTO result = brandService.updateBrand(brandId, brandDTO);

        assertNotNull(result);
        assertEquals(brandId, result.getId());
        assertEquals("Updated Brand", result.getName());

        verify(brandRepository, times(1)).findById(brandId);
        verify(brandRepository, times(1)).save(existingBrand);
    }
    @Test
    void testDeleteBrand() {
        Long brandId = 1L;
        Brand brand = new Brand();
        brand.setId(brandId);

        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));
        doNothing().when(brandRepository).delete(brand);

        brandService.deleteBrand(brandId);

        verify(brandRepository, times(1)).findById(brandId);
        verify(brandRepository, times(1)).delete(brand);
    }
}
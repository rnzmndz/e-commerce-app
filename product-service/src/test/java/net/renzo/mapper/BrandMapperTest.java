package net.renzo.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import net.renzo.dto.BrandDTO;
import net.renzo.model.Brand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BrandMapperTest {

    private final BrandMapper brandMapper = Mappers.getMapper(BrandMapper.class);

    @Test
    void testToBrandDTO() {
        // Arrange
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Test Brand");

        // Act
        BrandDTO brandDTO = brandMapper.toDTO(brand);

        // Assert
        assertNotNull(brandDTO);
        assertEquals(1L, brandDTO.getId());
        assertEquals("Test Brand", brandDTO.getName());
    }

    @Test
    void testToBrand() {
        // Arrange
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(1L);
        brandDTO.setName("Test Brand");

        // Act
        Brand brand = brandMapper.toEntity(brandDTO);

        // Assert
        assertNotNull(brand);
        assertEquals(1L, brand.getId());
        assertEquals("Test Brand", brand.getName());
    }
}
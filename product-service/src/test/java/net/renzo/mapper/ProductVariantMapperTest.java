package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import net.renzo.dto.ProductVariantDTO;
import net.renzo.model.ProductVariant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ProductVariantMapperTest {

    private ProductVariantMapper productVariantMapper;

    @BeforeEach
    void setUp() {
        productVariantMapper = Mappers.getMapper(ProductVariantMapper.class);
    }

    @Test
    void testToProductVariantDTO() {
        ProductVariant productVariant = new ProductVariant();
        productVariant.setId(1L);
        productVariant.setName("Variant 1");

        ProductVariantDTO productVariantDTO = productVariantMapper.toProductVariantDTO(productVariant);

        assertNotNull(productVariantDTO);
        assertEquals(1L, productVariantDTO.getId());
        assertEquals("Variant 1", productVariantDTO.getName());
    }

    @Test
    void testToProductVariant() {
        ProductVariantDTO productVariantDTO = new ProductVariantDTO();
        productVariantDTO.setId(1L);
        productVariantDTO.setName("Variant 1");

        ProductVariant productVariant = productVariantMapper.toProductVariant(productVariantDTO);

        assertNotNull(productVariant);
        assertEquals(1L, productVariant.getId());
        assertEquals("Variant 1", productVariant.getName());
    }

    @Test
    void testUpdateEntity() {
        ProductVariantDTO productVariantDTO = new ProductVariantDTO();
        productVariantDTO.setId(1L);
        productVariantDTO.setName("Updated Variant");

        ProductVariant productVariant = new ProductVariant();
        productVariant.setId(1L);
        productVariant.setName("Variant 1");

        productVariantMapper.updateEntity(productVariantDTO, productVariant);

        assertEquals(1L, productVariant.getId());
        assertEquals("Updated Variant", productVariant.getName());
    }
}
package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import net.renzo.dto.VariantDTO;
import net.renzo.model.Variant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class VariantMapperTest {

    private VariantMapper productVariantMapper;

    @BeforeEach
    void setUp() {
        productVariantMapper = Mappers.getMapper(VariantMapper.class);
    }

    @Test
    void testToProductVariantDTO() {
        Variant variant = new Variant();
        variant.setId(1L);
        variant.setName("Variant 1");

        VariantDTO variantDTO = productVariantMapper.toDto(variant);

        assertNotNull(variantDTO);
        assertEquals(1L, variantDTO.getId());
        assertEquals("Variant 1", variantDTO.getName());
    }

    @Test
    void testToProductVariant() {
        VariantDTO variantDTO = new VariantDTO();
        variantDTO.setId(1L);
        variantDTO.setName("Variant 1");

        Variant variant = productVariantMapper.toEntity(variantDTO);

        assertNotNull(variant);
        assertEquals(1L, variant.getId());
        assertEquals("Variant 1", variant.getName());
    }

    @Test
    void testUpdateEntity() {
        VariantDTO variantDTO = new VariantDTO();
        variantDTO.setId(1L);
        variantDTO.setName("Updated Variant");

        Variant variant = new Variant();
        variant.setId(1L);
        variant.setName("Variant 1");

        productVariantMapper.updateEntity(variantDTO, variant);

        assertEquals(1L, variant.getId());
        assertEquals("Updated Variant", variant.getName());
    }
}
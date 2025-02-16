package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import net.renzo.dto.ProductAttributeDTO;
import net.renzo.model.ProductAttribute;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ProductAttributeMapperTest {

    private final ProductAttributeMapper mapper = Mappers.getMapper(ProductAttributeMapper.class);

    @Test
    void testToDTO() {
        ProductAttribute productAttribute = new ProductAttribute();
        productAttribute.setId(1L);
        productAttribute.setKey("Color");
        productAttribute.setValue("Red");

        ProductAttributeDTO dto = mapper.toDto(productAttribute);

        assertNotNull(dto);
        assertEquals(productAttribute.getId(), dto.getId());
        assertEquals(productAttribute.getKey(), dto.getKey());
        assertEquals(productAttribute.getValue(), dto.getValue());
    }

    @Test
    void testToEntity() {
        ProductAttributeDTO dto = new ProductAttributeDTO();
        dto.setId(1L);
        dto.setKey("Color");
        dto.setValue("Red");

        ProductAttribute productAttribute = mapper.toEntity(dto);

        assertNotNull(productAttribute);
        assertEquals(dto.getId(), productAttribute.getId());
        assertEquals(dto.getKey(), productAttribute.getKey());
        assertEquals(dto.getValue(), productAttribute.getValue());
    }

@Test
        void testUpdateEntity() {
            ProductAttributeDTO dto = new ProductAttributeDTO();
            dto.setId(1L);
            dto.setKey("Color");
            dto.setValue("Blue");

            ProductAttribute productAttribute = new ProductAttribute();
            productAttribute.setId(1L);
            productAttribute.setKey("Color");
            productAttribute.setValue("Red");

            mapper.updateEntity(dto, productAttribute);

            assertNotNull(productAttribute);
            assertEquals(dto.getId(), productAttribute.getId());
            assertEquals(dto.getKey(), productAttribute.getKey());
            assertEquals(dto.getValue(), productAttribute.getValue());
        }
}
package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import net.renzo.dto.AttributeDTO;
import net.renzo.model.Attribute;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class AttributeMapperTest {

    private final ProductAttributeMapper mapper = Mappers.getMapper(ProductAttributeMapper.class);

    @Test
    void testToDTO() {
        Attribute attribute = new Attribute();
        attribute.setId(1L);
        attribute.setKey("Color");
        attribute.setValue("Red");

        AttributeDTO dto = mapper.toDto(attribute);

        assertNotNull(dto);
        assertEquals(attribute.getId(), dto.getId());
        assertEquals(attribute.getKey(), dto.getKey());
        assertEquals(attribute.getValue(), dto.getValue());
    }

    @Test
    void testToEntity() {
        AttributeDTO dto = new AttributeDTO();
        dto.setId(1L);
        dto.setKey("Color");
        dto.setValue("Red");

        Attribute attribute = mapper.toEntity(dto);

        assertNotNull(attribute);
        assertEquals(dto.getId(), attribute.getId());
        assertEquals(dto.getKey(), attribute.getKey());
        assertEquals(dto.getValue(), attribute.getValue());
    }

@Test
        void testUpdateEntity() {
            AttributeDTO dto = new AttributeDTO();
            dto.setId(1L);
            dto.setKey("Color");
            dto.setValue("Blue");

            Attribute attribute = new Attribute();
            attribute.setId(1L);
            attribute.setKey("Color");
            attribute.setValue("Red");

            mapper.updateEntity(dto, attribute);

            assertNotNull(attribute);
            assertEquals(dto.getId(), attribute.getId());
            assertEquals(dto.getKey(), attribute.getKey());
            assertEquals(dto.getValue(), attribute.getValue());
        }
}
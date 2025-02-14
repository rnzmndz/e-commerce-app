package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import net.renzo.dto.ProductUpdateDTO;
import net.renzo.model.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ProductUpdateMapperTest {

    private final ProductUpdateMapper mapper = Mappers.getMapper(ProductUpdateMapper.class);

    @Test
    void testToDto() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");

        ProductUpdateDTO dto = mapper.toDto(product);

        assertEquals("Test Product", dto.getName());
        assertEquals("Test Description", dto.getDescription());
    }

    @Test
    void testToEntity() {
        ProductUpdateDTO dto = new ProductUpdateDTO();
        dto.setName("Test Product");
        dto.setDescription("Test Description");

        Product product = mapper.toEntity(dto);

        assertEquals("Test Product", product.getName());
        assertEquals("Test Description", product.getDescription());
    }
}
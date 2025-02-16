package net.renzo.mapper;

import net.renzo.dto.ProductImageDTO;
import net.renzo.model.ProductImage;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductImageMapperTest {

    private final ProductImageMapper productImageMapper = Mappers.getMapper(ProductImageMapper.class);

    @Test
    void testEntityToDto() {
        ProductImage productImage = new ProductImage();
        productImage.setId(1L);
        productImage.setUrl("http://example.com/image.jpg");

        ProductImageDTO productImageDTO = productImageMapper.toDto(productImage);

        assertNotNull(productImageDTO);
        assertEquals(1L, productImageDTO.getId());
        assertEquals("http://example.com/image.jpg", productImageDTO.getUrl());
    }

    @Test
    void testDtoToEntity() {
        ProductImageDTO productImageDTO = new ProductImageDTO();
        productImageDTO.setId(1L);
        productImageDTO.setUrl("http://example.com/image.jpg");

        ProductImage productImage = productImageMapper.toEntity(productImageDTO);

        assertNotNull(productImage);
        assertEquals(1L, productImage.getId());
        assertEquals("http://example.com/image.jpg", productImage.getUrl());
    }

    @Test
    void testUpdateEntity() {
        ProductImage productImage = new ProductImage();
        productImage.setId(1L);
        productImage.setUrl("http://example.com/image.jpg");

        ProductImageDTO productImageDTO = new ProductImageDTO();
        productImageDTO.setId(2L);
        productImageDTO.setUrl("http://example.com/image2.jpg");

        productImageMapper.updateEntity(productImageDTO, productImage);

        assertNotNull(productImage);
        assertEquals(2L, productImage.getId());
        assertEquals("http://example.com/image2.jpg", productImage.getUrl());
    }
}
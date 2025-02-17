package net.renzo.mapper;

import net.renzo.dto.ImageDTO;
import net.renzo.model.Image;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageMapperTest {

    private final ProductImageMapper productImageMapper = Mappers.getMapper(ProductImageMapper.class);

    @Test
    void testEntityToDto() {
        Image image = new Image();
        image.setId(1L);
        image.setUrl("http://example.com/image.jpg");

        ImageDTO imageDTO = productImageMapper.toDto(image);

        assertNotNull(imageDTO);
        assertEquals(1L, imageDTO.getId());
        assertEquals("http://example.com/image.jpg", imageDTO.getUrl());
    }

    @Test
    void testDtoToEntity() {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(1L);
        imageDTO.setUrl("http://example.com/image.jpg");

        Image image = productImageMapper.toEntity(imageDTO);

        assertNotNull(image);
        assertEquals(1L, image.getId());
        assertEquals("http://example.com/image.jpg", image.getUrl());
    }

    @Test
    void testUpdateEntity() {
        Image image = new Image();
        image.setId(1L);
        image.setUrl("http://example.com/image.jpg");

        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(2L);
        imageDTO.setUrl("http://example.com/image2.jpg");

        productImageMapper.updateEntity(imageDTO, image);

        assertNotNull(image);
        assertEquals(2L, image.getId());
        assertEquals("http://example.com/image2.jpg", image.getUrl());
    }
}
package net.renzo.service;

import net.renzo.dto.ImageDTO;
import net.renzo.mapper.ProductImageMapper;
import net.renzo.model.Image;
import net.renzo.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private ProductImageMapper productImageMapper;

    @InjectMocks
    private ImageServiceImpl imageService;

    private ImageDTO imageDTO;
    private Image image;
    private Page<Image> imagePage;

    @BeforeEach
    void setUp() {
        imageDTO = new ImageDTO();
        imageDTO.setId(1L);
        imageDTO.setUrl("http://example.com/image.jpg");

        image = Image.builder()
                .id(1L)
                .url("http://example.com/image.jpg")
                .isPrimary(false)
                .build();

        imagePage = new PageImpl<>(Collections.singletonList(image), PageRequest.of(0, 10), 1);
    }

    @Test
    void save() {
        when(productImageMapper.toEntity(imageDTO)).thenReturn(image);
        when(imageRepository.save(image)).thenReturn(image);
        when(productImageMapper.toDto(image)).thenReturn(imageDTO);

        ImageDTO savedImageDTO = imageService.save(imageDTO);

        assertNotNull(savedImageDTO);
        assertEquals(imageDTO.getId(), savedImageDTO.getId());
        assertEquals(imageDTO.getUrl(), savedImageDTO.getUrl());
        verify(productImageMapper).toEntity(imageDTO);
        verify(imageRepository).save(image);
        verify(productImageMapper).toDto(image);
    }

    @Test
    void findById() {
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));
        when(productImageMapper.toDto(image)).thenReturn(imageDTO);

        ImageDTO foundImageDTO = imageService.findById(1L).orElseThrow();

        assertNotNull(foundImageDTO);
        assertEquals(imageDTO.getId(), foundImageDTO.getId());
        assertEquals(imageDTO.getUrl(), foundImageDTO.getUrl());
        verify(imageRepository).findById(1L);
        verify(productImageMapper).toDto(image);
    }

    @Test
    void findAll() {
        when(imageRepository.findAll(PageRequest.of(0, 10))).thenReturn(imagePage);
        when(productImageMapper.toDto(image)).thenReturn(imageDTO);

        Page<ImageDTO> imageDTOPage = imageService.findAll(PageRequest.of(0, 10));

        assertNotNull(imageDTOPage);
        assertEquals(1, imageDTOPage.getTotalElements());
        assertEquals(imageDTO.getId(), imageDTOPage.getContent().get(0).getId());
        verify(imageRepository).findAll(PageRequest.of(0, 10));
        verify(productImageMapper).toDto(image);
    }

    @Test
    void update() {
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));
        when(imageRepository.save(image)).thenReturn(image);
        when(productImageMapper.toDto(image)).thenReturn(imageDTO);
        doNothing().when(productImageMapper).updateEntity(imageDTO, image);

        ImageDTO updatedImageDTO = imageService.update(1L, imageDTO);

        assertNotNull(updatedImageDTO);
        assertEquals(imageDTO.getId(), updatedImageDTO.getId());
        assertEquals(imageDTO.getUrl(), updatedImageDTO.getUrl());
        verify(imageRepository).findById(1L);
        verify(productImageMapper).updateEntity(imageDTO, image);
        verify(imageRepository).save(image);
        verify(productImageMapper).toDto(image);
    }

    @Test
    void deleteById() {
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));

        imageService.deleteById(1L);

        verify(imageRepository).findById(1L);
        verify(imageRepository).delete(image);
    }
}
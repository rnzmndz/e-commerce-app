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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        MockitoAnnotations.openMocks(this);
        imageDTO = new ImageDTO();
        imageDTO.setId(1L);
        imageDTO.setUrl("http://example.com/image.jpg");

        image = new Image();
        image.setId(1L);
        image.setUrl("http://example.com/image.jpg");

        imagePage = new PageImpl<>(Collections.singletonList(image), PageRequest.of(0, 10), 1);
    }

    @Test
    void save() {
        // Arrange
        when(productImageMapper.toEntity(imageDTO)).thenReturn(image);
        when(imageRepository.save(image)).thenReturn(image);
        when(productImageMapper.toDto(image)).thenReturn(imageDTO);

        // Act
        ImageDTO savedImageDTO = imageService.save(imageDTO);

        // Assert
        assertNotNull(savedImageDTO);
        assertEquals(imageDTO.getId(), savedImageDTO.getId());
        assertEquals(imageDTO.getUrl(), savedImageDTO.getUrl());
        verify(productImageMapper).toEntity(imageDTO);
        verify(imageRepository).save(image);
        verify(productImageMapper).toDto(image);
    }

    @Test
    void findById() {
        // Arrange
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));
        when(productImageMapper.toDto(image)).thenReturn(imageDTO);

        // Act
        Optional<ImageDTO> foundImageDTO = imageService.findById(1L);

        // Assert
        assertTrue(foundImageDTO.isPresent());
        assertEquals(imageDTO.getId(), foundImageDTO.get().getId());
        assertEquals(imageDTO.getUrl(), foundImageDTO.get().getUrl());
        verify(imageRepository).findById(1L);
        verify(productImageMapper).toDto(image);
    }

    @Test
    void findAll() {
        // Arrange
        when(imageRepository.findAll(PageRequest.of(0, 10))).thenReturn(imagePage);
        when(productImageMapper.toDto(image)).thenReturn(imageDTO);

        // Act
        Page<ImageDTO> imageDTOPage = imageService.findAll(PageRequest.of(0, 10));

        // Assert
        assertNotNull(imageDTOPage);
        assertEquals(1, imageDTOPage.getTotalElements());
        assertEquals(imageDTO.getId(), imageDTOPage.getContent().get(0).getId());
        verify(imageRepository).findAll(PageRequest.of(0, 10));
        verify(productImageMapper).toDto(image);
    }

    @Test
    void update() {
        // Arrange
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));
        when(productImageMapper.toDto(image)).thenReturn(imageDTO);

        // Act
        ImageDTO updatedImageDTO = imageService.update(imageDTO);

        // Assert
        assertNotNull(updatedImageDTO);
        assertEquals(imageDTO.getId(), updatedImageDTO.getId());
        assertEquals(imageDTO.getUrl(), updatedImageDTO.getUrl());
        verify(imageRepository).findById(1L);
        verify(imageRepository).save(image);
        verify(productImageMapper).toDto(image);
    }

    @Test
    void deleteById() {
        // Arrange
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));

        // Act
        imageService.deleteById(1L);

        // Assert
        verify(imageRepository).findById(1L);
        verify(imageRepository).delete(image);
    }
}
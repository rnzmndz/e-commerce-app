package net.renzo.service;

import net.renzo.dto.AttributeDTO;
import net.renzo.mapper.ProductAttributeMapper;
import net.renzo.model.Attribute;
import net.renzo.model.Product;
import net.renzo.repository.AttributeRepository;
import net.renzo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AttributeServiceImplTest {

    @Mock
    private AttributeRepository attributeRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductAttributeMapper productAttributeMapper;

    @InjectMocks
    private AttributeServiceImpl attributeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        AttributeDTO attributeDTO = new AttributeDTO();
        Attribute attribute = new Attribute();

        when(productAttributeMapper.toEntity(any(AttributeDTO.class))).thenReturn(attribute);
        when(attributeRepository.save(any(Attribute.class))).thenReturn(attribute);
        when(productAttributeMapper.toDto(any(Attribute.class))).thenReturn(attributeDTO);

        AttributeDTO result = attributeService.save(attributeDTO);

        assertEquals(attributeDTO, result);
    }

    @Test
    void testFindById() {
        Attribute attribute = new Attribute();
        AttributeDTO attributeDTO = new AttributeDTO();

        when(attributeRepository.findById(anyLong())).thenReturn(Optional.of(attribute));
        when(productAttributeMapper.toDto(any(Attribute.class))).thenReturn(attributeDTO);

        Optional<AttributeDTO> result = attributeService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(attributeDTO, result.get());
    }

    @Test
    void testFindAll() {
        Attribute attribute = new Attribute();
        AttributeDTO attributeDTO = new AttributeDTO();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Attribute> page = new PageImpl<>(Collections.singletonList(attribute));

        when(attributeRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(productAttributeMapper.toDto(any(Attribute.class))).thenReturn(attributeDTO);

        Page<AttributeDTO> result = attributeService.findAll(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(attributeDTO, result.getContent().get(0));
    }

    @Test
    void testUpdate() {
        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setId(1L);
        Attribute attribute = new Attribute();

        when(attributeRepository.findById(anyLong())).thenReturn(Optional.of(attribute));
        when(attributeRepository.save(any(Attribute.class))).thenReturn(attribute);
        when(productAttributeMapper.toDto(any(Attribute.class))).thenReturn(attributeDTO);

        AttributeDTO result = attributeService.update(attributeDTO);

        assertEquals(attributeDTO, result);
    }

    @Test
    void testAddAttributeToProduct() {
        Attribute attribute = new Attribute();
        attribute.setProducts(new HashSet<>());
        Product product = new Product();

        when(attributeRepository.findById(anyLong())).thenReturn(Optional.of(attribute));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        attributeService.addAttributeToProduct(1L, 1L);

        verify(attributeRepository, times(1)).save(attribute);
    }

    @Test
    void testRemoveAttributeFromProduct() {
        Attribute attribute = new Attribute();
        Product product = new Product();
        attribute.setProducts(new HashSet<>());

        when(attributeRepository.findById(anyLong())).thenReturn(Optional.of(attribute));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        attributeService.removeAttributeFromProduct(1L, 1L);

        verify(attributeRepository, times(1)).save(attribute);
    }

    @Test
    void testDeleteById() {
        Attribute attribute = new Attribute();

        when(attributeRepository.findById(anyLong())).thenReturn(Optional.of(attribute));

        attributeService.deleteById(1L);

        verify(attributeRepository, times(1)).delete(attribute);
    }
}
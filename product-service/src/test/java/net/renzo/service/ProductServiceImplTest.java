package net.renzo.service;

import net.renzo.dto.ProductCreateDTO;
import net.renzo.dto.ProductDTO;
import net.renzo.dto.ProductUpdateDTO;
import net.renzo.mapper.*;
import net.renzo.model.Product;
import net.renzo.repository.ProductRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    ProductMapper productMapper;
    @Mock
    ProductCreateMapper productCreateMapper;
    @Mock
    CategoryMapper categoryMapper;
    @Mock
    BrandMapper brandMapper;
    @Mock
    ProductImageMapper imageMapper;
    @Mock
    VariantMapper variantMapper;
    @Mock
    ProductAttributeMapper attributeMapper;
    @Mock
    ProductReviewMapper reviewMapper;
    @Mock
    ProductUpdateMapper productUpdateMapper;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct() {
        // Arrange
        ProductCreateDTO productCreateDTO = new ProductCreateDTO();

        Product product = new Product();
        // Set necessary fields for product

        when(productCreateMapper.toEntity(productCreateDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(new ProductDTO());

        ProductServiceImpl productService = new ProductServiceImpl(productRepository, productMapper, productCreateMapper, categoryMapper, brandMapper, imageMapper, variantMapper, attributeMapper, reviewMapper, productUpdateMapper);

        // Act
        ProductDTO result = productService.createProduct(productCreateDTO);

        // Assert
        assertNotNull(result);
        verify(productRepository).save(product);
    }

    @Test
    void findProductById() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        // Set necessary fields for product

        ProductRepository productRepository = mock(ProductRepository.class);
        ProductMapper productMapper = mock(ProductMapper.class);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(new ProductDTO());

        ProductServiceImpl productService = new ProductServiceImpl(productRepository, productMapper, null, null, null, null, null, null, null, null);

        // Act
        Optional<ProductDTO> result = productService.findProductById(productId);

        // Assert
        assertTrue(result.isPresent());
        verify(productRepository).findById(productId);
    }

    @Test
    void findProductByName() {
        // Arrange
        String productName = "Test Product";
        Product product = new Product();
        // Set necessary fields for product

        when(productRepository.findByName(productName)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(new ProductDTO());

        ProductServiceImpl productService = new ProductServiceImpl(productRepository, productMapper, null, null, null, null, null, null, null, null);

        // Act
        Optional<ProductDTO> result = productService.findProductByName(productName);

        // Assert
        assertTrue(result.isPresent());
        verify(productRepository).findByName(productName);
    }

    @Test
    void findAllProducts() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(new Product()));
        // Set necessary fields for productPage

        when(productRepository.findAll(pageable)).thenReturn(productPage);
        when(productMapper.toDto(any(Product.class))).thenReturn(new ProductDTO());

        ProductServiceImpl productService = new ProductServiceImpl(productRepository, productMapper, null, null, null, null, null, null, null, null);

        // Act
        Page<ProductDTO> result = productService.findAllProducts(pageable);

        // Assert
        assertNotNull(result);
        verify(productRepository).findAll(pageable);
    }

    @Test
    void updateProduct() {
        // Arrange
        Long productId = 1L;
        ProductUpdateDTO productUpdateDTO = new ProductUpdateDTO();
        // Set necessary fields for productUpdateDTO

        Product product = new Product();
        // Set necessary fields for product

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(new ProductDTO());

        ProductServiceImpl productService = new ProductServiceImpl(productRepository, productMapper, null, null, null, null, null, null, null, productUpdateMapper);

        // Act
        ProductDTO result = productService.updateProduct(productId, productUpdateDTO);

        // Assert
        assertNotNull(result);
        verify(productRepository).save(product);
    }

    @Test
    void deleteProduct() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        // Set necessary fields for product

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ProductServiceImpl productService = new ProductServiceImpl(productRepository, null, null, null, null, null, null, null, null, null);

        // Act
        productService.deleteProduct(productId);

        // Assert
        verify(productRepository).delete(product);
    }
}
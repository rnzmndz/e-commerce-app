package net.renzo.mapper;

import net.renzo.dto.CategoryDTO;
import net.renzo.dto.ProductDetailDTO;
import net.renzo.model.Brand;
import net.renzo.model.Category;
import net.renzo.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductDetailMapperTest {

    @InjectMocks
    private ProductDetailMapperImpl mapper;

    @Mock
    private ProductImageMapper productImageMapper;

    @Mock
    private ProductAttributeMapper productAttributeMapper;

    @Mock
    private ProductReviewMapper productReviewMapper;

    @Mock
    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToDto() {
        // Setup test data
        Product product = new Product();
        product.setId(1L);
        product.setName("Smartphone");
        product.setDescription("A high-end smartphone with 128GB storage.");
        product.setSku("SKU12345");

        Category category = Category.builder()
                .id(1L)
                .name("Electronics")
                .description("Electronic devices")
                .products(new HashSet<>())
                .build();
        product.setCategory(category);

        product.setBrand(Brand.builder()
                .id(1L)
                .name("Apple")
                .products(new HashSet<>())
                .build());

        // Mock category mapping
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Electronics")
                .description("Electronic devices")
                .build();
        lenient().when(categoryMapper.toDto(any(Category.class)))
                .thenReturn(categoryDTO);

        // Execute
        ProductDetailDTO dto = mapper.toDto(product);

        // Verify
        assertNotNull(dto);
        assertEquals(product.getId(), dto.getId());
        assertEquals(product.getName(), dto.getName());
        assertEquals(product.getDescription(), dto.getDescription());
        assertEquals(product.getSku(), dto.getSku());
        assertNotNull(dto.getCategory());
        assertEquals(category.getId(), dto.getCategory().getId());
        assertEquals(category.getName(), dto.getCategory().getName());
        assertEquals(category.getDescription(), dto.getCategory().getDescription());
        assertEquals(product.getBrand().getName(), dto.getBrandName());
    }

    @Test
    void testToEntity() {
        // Setup test data
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setId(1L);
        dto.setName("Smartphone");
        dto.setDescription("A high-end smartphone with 128GB storage.");
        dto.setSku("SKU12345");

        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Electronics")
                .description("Electronic devices")
                .build();
        dto.setCategory(categoryDTO);

        dto.setBrandName("Apple");

        // Mock category mapping
        Category category = Category.builder()
                .id(1L)
                .name("Electronics")
                .build();
        when(categoryMapper.toEntity(any(CategoryDTO.class)))
                .thenReturn(category);

        // Execute
        Product product = mapper.toEntity(dto);

        // Verify
        assertNotNull(product);
        assertEquals(dto.getId(), product.getId());
        assertEquals(dto.getName(), product.getName());
        assertEquals(dto.getDescription(), product.getDescription());
        assertEquals(dto.getSku(), product.getSku());
        assertEquals(dto.getBrandName(), product.getBrand().getName());
    }
}
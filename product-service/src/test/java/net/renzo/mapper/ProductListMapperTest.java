package net.renzo.mapper;

import net.renzo.dto.CategoryDTO;
import net.renzo.dto.ProductListDTO;
import net.renzo.model.Brand;
import net.renzo.model.Category;
import net.renzo.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ProductListMapperTest {

    @InjectMocks
    private ProductListMapperImpl mapper;

    @Mock
    private ProductImageMapper productImageMapper;

    @Mock
    private ProductAttributeMapper productAttributeMapper;

    @Mock
    private ProductReviewMapper productReviewMapper;

    @Mock
    private CategoryMapper productCategoryMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToProductListDTO() {
        // Prepare test data
        Product product = new Product();
        Category category = Category.builder()
                .id(1L)
                .name("Electronics")
                .description("Electronic devices")
                .products(new HashSet<>())
                .build();
        product.addCategory(category);
        Brand brand = Brand.builder()
                .id(1L)
                .name("Apple")
                .products(new HashSet<>())
                .build();
        product.setBrand(brand);

        // Prepare expected DTO
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Electronics")
                .description("Electronic devices")
                .build();
        when(productCategoryMapper.toDto(category)).thenReturn(categoryDTO);

        // Execute test
        ProductListDTO dto = mapper.toProductListDTO(product);

        // Verify results
        assertNotNull(dto);
        assertEquals(1, dto.getCategories().size());
        assertEquals("Electronics", dto.getCategories().iterator().next().getName());
        assertEquals("Apple", dto.getBrandName());
        assertEquals(1L, dto.getCategories().iterator().next().getId());
    }

    @Test
    void testToProduct() {
        ProductListDTO dto = new ProductListDTO();
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Electronics")
                .build();
        dto.setCategories(Set.of(categoryDTO));
        dto.setBrandName("BrandName");

        Category category = Category.builder()
                .id(1L)
                .name("Electronics")
                .products(new HashSet<>())
                .build();
        when(productCategoryMapper.toEntity(categoryDTO)).thenReturn(category);

        Product product = mapper.toProduct(dto);

        assertEquals("Electronics", product.getCategories().iterator().next().getName());
        assertEquals("BrandName", product.getBrand().getName());
    }
}
package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import net.renzo.dto.*;
import net.renzo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class ProductCreateMapperTest {

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private BrandMapper brandMapper;

    @Mock
    private ProductImageMapper productImageMapper;

    @Mock
    private VariantMapper productVariantMapper;

    @Mock
    private ProductAttributeMapper productAttributeMapper;

    @Mock
    private ProductReviewMapper productReviewMapper;

    @InjectMocks
    private ProductCreateMapperImpl productCreateMapper;

    private ProductCreateDTO productCreateDTO;
    private Category mockCategory;
    private Brand mockBrand;
    private Image mockImage;
    private Variant mockVariant;
    private Attribute mockAttribute;
    private Review mockReview;

    @BeforeEach
    void setUp() {
        // Initialize test data
        productCreateDTO = new ProductCreateDTO();
        productCreateDTO.setName("Test Product");
        productCreateDTO.setDescription("Test Description");
        productCreateDTO.setCategory(new CategoryDTO(1L, "CategoryName", "CategoryDescription", null));
        productCreateDTO.setBrand(new BrandDTO(1L, "BrandName", "BrandDescription", null));
        productCreateDTO.setImages(Set.of(new ImageDTO(1L, "ImageURL", 0L)));
        productCreateDTO.setVariants(Collections.singleton(new VariantDTO(1L, "VariantName", new PriceDTO(1L, 50.0, "USD"), 10, null)));
        productCreateDTO.setAttributes(Collections.singleton(new AttributeDTO(1L, "AttributeName", "AttributeValue")));
        productCreateDTO.setReviews(Collections.singleton(new ReviewDTO(1L, 5, "ReviewContent", 5L)));

        // Initialize mock return objects
        mockCategory = new Category();
        mockCategory.setId(1L);
        mockBrand = new Brand();
        mockBrand.setId(1L);
        mockImage = new Image();
        mockImage.setId(1L);
        mockVariant = new Variant();
        mockVariant.setId(1L);
        mockAttribute = new Attribute();
        mockAttribute.setId(1L);
        mockReview = new Review();
        mockReview.setId(1L);

        // Configure mock behaviors
        lenient().when(categoryMapper.toEntity(any(CategoryDTO.class))).thenReturn(mockCategory);
        lenient().when(brandMapper.toEntity(any(BrandDTO.class))).thenReturn(mockBrand);
        lenient().when(productImageMapper.toEntity(any(ImageDTO.class))).thenReturn(mockImage);
        lenient().when(productVariantMapper.toEntity(any(VariantDTO.class))).thenReturn(mockVariant);
        lenient().when(productAttributeMapper.toEntity(any(AttributeDTO.class))).thenReturn(mockAttribute);
        lenient().when(productReviewMapper.toEntity(any(ReviewDTO.class))).thenReturn(mockReview);
    }

    @Test
    void testToEntity_WithValidDTO_ShouldMapAllFields() {
        // Act
        Product product = productCreateMapper.toEntity(productCreateDTO);

        // Assert
        assertNotNull(product, "Product should not be null");
        assertEquals("Test Product", product.getName(), "Product name should match");
        assertEquals("Test Description", product.getDescription(), "Product description should match");

        // Verify category mapping
        assertNotNull(product.getCategory(), "Categories should not be null");
        assertEquals(1L, product.getCategory().getId(), "Category ID should match");
        verify(categoryMapper).toEntity(any(CategoryDTO.class));

        // Verify brand mapping
        assertNotNull(product.getBrand(), "Brand should not be null");
        assertEquals(1L, product.getBrand().getId(), "Brand ID should match");
        verify(brandMapper).toEntity(any(BrandDTO.class));

        // Verify images mapping
        assertNotNull(product.getImages(), "Images set should not be null");
        assertEquals(1, product.getImages().size(), "Should have one image");
        verify(productImageMapper).toEntity(any(ImageDTO.class));

        // Verify variants mapping
        assertNotNull(product.getVariants(), "Variants set should not be null");
        assertEquals(1, product.getVariants().size(), "Should have one variant");
        verify(productVariantMapper).toEntity(any(VariantDTO.class));

        // Verify attributes mapping
        assertNotNull(product.getAttributes(), "Attributes set should not be null");
        assertEquals(1, product.getAttributes().size(), "Should have one attribute");
        verify(productAttributeMapper).toEntity(any(AttributeDTO.class));

        // Verify reviews mapping
        assertNotNull(product.getReviews(), "Reviews set should not be null");
        assertEquals(1, product.getReviews().size(), "Should have one review");
        verify(productReviewMapper).toEntity(any(ReviewDTO.class));
    }

    @Test
    void testToEntity_WithNullDTO_ShouldReturnNull() {
        // Act
        Product product = productCreateMapper.toEntity(null);

        // Assert
        assertNull(product, "Product should be null when DTO is null");
    }

    @Test
    void testToEntity_WithEmptyCollections_ShouldMapCorrectly() {
        // Arrange
        productCreateDTO.setImages(new HashSet<>());
        productCreateDTO.setVariants(new HashSet<>());
        productCreateDTO.setAttributes(new HashSet<>());
        productCreateDTO.setReviews(new HashSet<>());

        // Act
        Product product = productCreateMapper.toEntity(productCreateDTO);

        // Assert
        assertNotNull(product, "Product should not be null");
        assertTrue(product.getImages().isEmpty(), "Images set should be empty");
        assertTrue(product.getVariants().isEmpty(), "Variants set should be empty");
        assertTrue(product.getAttributes().isEmpty(), "Attributes set should be empty");
        assertTrue(product.getReviews().isEmpty(), "Reviews set should be empty");
    }
}
package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import net.renzo.dto.BrandDTO;
import net.renzo.dto.CategoryDTO;
import net.renzo.dto.ProductCreateDTO;
import net.renzo.dto.ProductImageDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;
//TODO Create a test for the ProductCreateMapper class
@ExtendWith(MockitoExtension.class)
class ProductCreateMapperTest {

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private BrandMapper brandMapper;

    @Mock
    private ProductImageMapper productImageMapper;

    @Mock
    private ProductVariantMapper productVariantMapper;

    @Mock
    private ProductAttributeMapper productAttributeMapper;

    @Mock
    private ProductReviewMapper productReviewMapper;

    @InjectMocks
    private ProductCreateMapper productCreateMapper;

    @Test
    void testProductMapping() {
        // Arrange
        ProductCreateDTO productCreateDTO = new ProductCreateDTO();
        productCreateDTO.setCategory(new CategoryDTO(1L, "CategoryName", "CategoryDescription", null));
        productCreateDTO.setBrand(new BrandDTO(1L, "BrandName", "BrandDescription", null));
        productCreateDTO.setImages(Set.of(new ProductImageDTO(1L, "ImageURL", 0L)));
        productCreateDTO.setVariants(Collections.emptyList());
        productCreateDTO.setAttributes(Collections.emptyList());
        productCreateDTO.setReviews(Collections.emptyList());


        // Act
        // (Call the method to be tested here)

        // Assert
        // (Verify the results here)
    }
}
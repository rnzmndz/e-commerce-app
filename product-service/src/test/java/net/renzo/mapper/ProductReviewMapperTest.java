package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import net.renzo.dto.ProductReviewDTO;
import net.renzo.model.ProductReview;
import net.renzo.model.UserDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ProductReviewMapperTest {

    private ProductReviewMapper productReviewMapper;

    @BeforeEach
    void setUp() {
        productReviewMapper = Mappers.getMapper(ProductReviewMapper.class);
    }

    @Test
    void testToDto() {
        ProductReview productReview = new ProductReview();
        productReview.setId(1L);
        productReview.setUserDetail(new UserDetail());

        ProductReviewDTO productReviewDTO = productReviewMapper.toDto(productReview);

        assertNotNull(productReviewDTO);
        assertEquals(productReview.getId(), productReviewDTO.getId());

    }

    @Test
    void testToEntity() {
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setId(1L);
        productReviewDTO.setUserId(1L);

        ProductReview productReview = productReviewMapper.toEntity(productReviewDTO);

        assertNotNull(productReview);
        assertEquals(productReviewDTO.getId(), productReview.getId());
        assertEquals(productReviewDTO.getUserId(), productReview.getUserDetail().getId());
    }

    @Test
    void testUpdateEntity() {
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setId(1L);
        productReviewDTO.setUserId(1L);

        ProductReview productReview = new ProductReview();
        productReview.setId(1L);
        productReview.setUserDetail(new UserDetail());

        productReviewMapper.updateEntity(productReviewDTO, productReview);

        assertEquals(productReviewDTO.getId(), productReview.getId());
    }
}
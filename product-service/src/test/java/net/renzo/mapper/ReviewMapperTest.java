package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import net.renzo.dto.ReviewDTO;
import net.renzo.model.Review;
import net.renzo.model.UserDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ReviewMapperTest {

    private ProductReviewMapper productReviewMapper;

    @BeforeEach
    void setUp() {
        productReviewMapper = Mappers.getMapper(ProductReviewMapper.class);
    }

    @Test
    void testToDto() {
        Review review = new Review();
        review.setId(1L);
        review.setUserDetail(new UserDetail());

        ReviewDTO reviewDTO = productReviewMapper.toDto(review);

        assertNotNull(reviewDTO);
        assertEquals(review.getId(), reviewDTO.getId());

    }

    @Test
    void testToEntity() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(1L);
        reviewDTO.setUserId(1L);

        Review review = productReviewMapper.toEntity(reviewDTO);

        assertNotNull(review);
        assertEquals(reviewDTO.getId(), review.getId());
        assertEquals(reviewDTO.getUserId(), review.getUserDetail().getId());
    }

    @Test
    void testUpdateEntity() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(1L);
        reviewDTO.setUserId(1L);

        Review review = new Review();
        review.setId(1L);
        review.setUserDetail(new UserDetail());

        productReviewMapper.updateEntity(reviewDTO, review);

        assertEquals(reviewDTO.getId(), review.getId());
    }
}
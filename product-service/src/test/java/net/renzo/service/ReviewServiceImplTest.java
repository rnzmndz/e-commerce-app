package net.renzo.service;

import net.renzo.dto.ReviewDTO;
import net.renzo.mapper.ProductReviewMapper;
import net.renzo.model.Review;
import net.renzo.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ProductReviewMapper productReviewMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    void testCreateProductReview() {
        ReviewDTO reviewDTO = new ReviewDTO();
        Review review = new Review();
        when(productReviewMapper.toEntity(reviewDTO)).thenReturn(review);
        when(reviewRepository.save(review)).thenReturn(review);
        when(productReviewMapper.toDto(review)).thenReturn(reviewDTO);

        ReviewDTO result = reviewService.createProductReview(reviewDTO);

        assertEquals(reviewDTO, result);
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    void testGetProductReviewById() {
        Long id = 1L;
        Review review = new Review();
        ReviewDTO reviewDTO = new ReviewDTO();
        when(reviewRepository.findById(id)).thenReturn(Optional.of(review));
        when(productReviewMapper.toDto(review)).thenReturn(reviewDTO);

        Optional<ReviewDTO> result = reviewService.getProductReviewById(id);

        assertTrue(result.isPresent());
        assertEquals(reviewDTO, result.get());
    }

    @Test
    void testGetAllProductReview() {
        Pageable pageable = PageRequest.of(0, 10);
        Review review = new Review();
        ReviewDTO reviewDTO = new ReviewDTO();
        Page<Review> reviewPage = new PageImpl<>(Collections.singletonList(review));
        when(reviewRepository.findAll(pageable)).thenReturn(reviewPage);
        when(productReviewMapper.toDto(review)).thenReturn(reviewDTO);

        Page<ReviewDTO> result = reviewService.getAllProductReview(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(reviewDTO, result.getContent().get(0));
    }

    @Test
    void testGetProductReviewByProductId() {
        Long productId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Review review = new Review();
        ReviewDTO reviewDTO = new ReviewDTO();
        Page<Review> reviewPage = new PageImpl<>(Collections.singletonList(review));
        when(reviewRepository.findByProductId(productId, pageable)).thenReturn(reviewPage);
        when(productReviewMapper.toDto(review)).thenReturn(reviewDTO);

        Page<ReviewDTO> result = reviewService.getProductReviewByProductId(productId, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(reviewDTO, result.getContent().get(0));
    }

    @Test
    void testUpdateProductReview() {
        Long id = 1L;
        ReviewDTO reviewDTO = new ReviewDTO();
        Review review = new Review();
        when(reviewRepository.findById(id)).thenReturn(Optional.of(review));
        doNothing().when(productReviewMapper).updateEntity(reviewDTO, review);
        when(reviewRepository.save(review)).thenReturn(review);
        when(productReviewMapper.toDto(review)).thenReturn(reviewDTO);

        ReviewDTO result = reviewService.updateProductReview(id, reviewDTO);

        assertEquals(reviewDTO, result);
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    void testDeleteProductReview() {
        Long id = 1L;
        Review review = new Review();
        when(reviewRepository.findById(id)).thenReturn(Optional.of(review));
        doNothing().when(reviewRepository).delete(review);

        reviewService.deleteProductReview(id);

        verify(reviewRepository, times(1)).delete(review);
    }
}
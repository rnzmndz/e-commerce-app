package net.renzo.controller;

import net.renzo.dto.ReviewDTO;
import net.renzo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> save(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.createProductReview(reviewDTO);
        return ResponseEntity.ok(createdReview);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getProductReviewById(@PathVariable Long id) {
        Optional<ReviewDTO> reviewDTO = reviewService.getProductReviewById(id);
        return reviewDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<ReviewDTO>> findAll(Pageable pageable) {
        Page<ReviewDTO> reviews = reviewService.getAllProductReview(pageable);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Page<ReviewDTO>> findReviewByProductId(@PathVariable Long productId, Pageable pageable) {
        Page<ReviewDTO> reviews = reviewService.getProductReviewByProductId(productId, pageable);
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> update(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO updatedReview = reviewService.updateProductReview(id, reviewDTO);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.deleteProductReview(id);
        return ResponseEntity.noContent().build();
    }
}
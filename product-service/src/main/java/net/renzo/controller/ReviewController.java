package net.renzo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Review Controller", description = "Endpoints for managing product reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "Create new review",
            description = "Creates a new product review with the provided details")
    @ApiResponse(responseCode = "200", description = "Review created successfully")
    @PostMapping
    public ResponseEntity<ReviewDTO> save(
            @Parameter(description = "Review details") @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.createProductReview(reviewDTO);
        return ResponseEntity.ok(createdReview);
    }

    @Operation(summary = "Find review by ID",
            description = "Retrieves a specific review by its ID")
    @ApiResponse(responseCode = "200", description = "Review found")
    @ApiResponse(responseCode = "404", description = "Review not found")
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getProductReviewById(
            @Parameter(description = "ID of the review") @PathVariable Long id) {
        Optional<ReviewDTO> reviewDTO = reviewService.getProductReviewById(id);
        return reviewDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all reviews",
            description = "Retrieves a paginated list of all reviews")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved reviews")
    @GetMapping
    public ResponseEntity<Page<ReviewDTO>> findAll(Pageable pageable) {
        Page<ReviewDTO> reviews = reviewService.getAllProductReview(pageable);
        return ResponseEntity.ok(reviews);
    }

    @Operation(summary = "Find reviews by product ID",
            description = "Retrieves a paginated list of reviews for a specific product")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved product reviews")
    @GetMapping("/product/{productId}")
    public ResponseEntity<Page<ReviewDTO>> findReviewByProductId(
            @Parameter(description = "ID of the product") @PathVariable Long productId,
            Pageable pageable) {
        Page<ReviewDTO> reviews = reviewService.getProductReviewByProductId(productId, pageable);
        return ResponseEntity.ok(reviews);
    }

    @Operation(summary = "Update review",
            description = "Updates an existing review by its ID")
    @ApiResponse(responseCode = "200", description = "Review updated successfully")
    @ApiResponse(responseCode = "404", description = "Review not found")
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> update(
            @Parameter(description = "ID of the review to update") @PathVariable Long id,
            @Parameter(description = "Updated review details") @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO updatedReview = reviewService.updateProductReview(id, reviewDTO);
        return ResponseEntity.ok(updatedReview);
    }

    @Operation(summary = "Delete review",
            description = "Deletes a review by its ID")
    @ApiResponse(responseCode = "204", description = "Review deleted successfully")
    @ApiResponse(responseCode = "404", description = "Review not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the review to delete") @PathVariable Long id) {
        reviewService.deleteProductReview(id);
        return ResponseEntity.noContent().build();
    }
}
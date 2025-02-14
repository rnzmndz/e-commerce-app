package net.renzo.repository;

import net.renzo.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
}

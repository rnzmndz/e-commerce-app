package net.renzo.repository;

import net.renzo.model.Variant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VariantRepository extends JpaRepository<Variant, Long> {
    Page<Variant> findByProductId(Long productId, Pageable pageable);
}

package net.renzo.repository;

import net.renzo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    Optional<Product> findByName(String name);
}

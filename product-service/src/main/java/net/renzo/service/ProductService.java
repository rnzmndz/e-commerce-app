package net.renzo.service;

import net.renzo.dto.ProductDTO;
import net.renzo.dto.ProductUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    /**
     * Creates a new product.
     *
     * @param product the product to create
     * @return the created product
     */
    ProductDTO createProduct(ProductDTO productDTO);

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product
     * @return an Optional containing the found product, or empty if not found
     */
    Optional<ProductDTO> findProductById(Long id);

    /**
     * Finds a product by its name.
     *
     * @param name the name of the product
     * @return an Optional containing the found product, or empty if not found
     */
    Optional<ProductDTO> findProductByName(String name);

    /**
     * Finds all products.
     *
     * @return a list of all products
     */
    Page<ProductDTO> findAllProducts(Pageable pageable);

    /**
     * Updates an existing product.
     *
     * @param id the ID of the product to update
     * @param product the updated product data
     * @return the updated product
     */
    ProductDTO updateProduct(Long id, ProductUpdateDTO product);

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    void deleteProduct(Long id);
}
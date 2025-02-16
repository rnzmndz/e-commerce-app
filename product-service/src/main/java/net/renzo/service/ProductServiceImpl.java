package net.renzo.service;

import net.renzo.dto.ProductDTO;
import net.renzo.dto.ProductUpdateDTO;
import net.renzo.mapper.ProductMapper;
import net.renzo.model.Product;
import net.renzo.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        // Convert the ProductDTO to a Product entity
        Product product = productMapper.toEntity(productDTO);



        // Set the creation timestamp to the current time
        product.setCreatedAt(LocalDateTime.now());

        // Set the update timestamp to the current time
        product.setUpdatedAt(LocalDateTime.now());

        // Save the new product to the repository
        product = productRepository.save(product);

        // Convert the saved Product entity to a ProductDTO and return it
        return productMapper.toDto(product);
    }

    @Override
    public Optional<ProductDTO> findProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductDTO> findProductByName(String name) {
        return Optional.empty();
    }

    @Override
    public Page<ProductDTO> findAllProducts(Pageable pageable) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductUpdateDTO product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}

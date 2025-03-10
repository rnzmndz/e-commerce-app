package net.renzo.service;

import net.renzo.exception.ProductNotFoundException;
import net.renzo.dto.ProductCreateDTO;
import net.renzo.dto.ProductDTO;
import net.renzo.dto.ProductUpdateDTO;
import net.renzo.mapper.*;
import net.renzo.model.Product;
import net.renzo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductCreateMapper productCreateMapper;
    private final CategoryMapper categoryMapper;
    private final BrandMapper brandMapper;
    private final ProductImageMapper imageMapper;
    private final VariantMapper variantMapper;
    private final ProductAttributeMapper attributeMapper;
    private final ProductReviewMapper reviewMapper;
    private final ProductUpdateMapper productUpdateMapper;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,
                              ProductCreateMapper productCreateMapper, CategoryMapper categoryMapper,
                              BrandMapper brandMapper, ProductImageMapper imageMapper, VariantMapper variantMapper,
                              ProductAttributeMapper attributeMapper, ProductReviewMapper reviewMapper,
                              ProductUpdateMapper productUpdateMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productCreateMapper = productCreateMapper;
        this.categoryMapper = categoryMapper;
        this.brandMapper = brandMapper;
        this.imageMapper = imageMapper;
        this.variantMapper = variantMapper;
        this.attributeMapper = attributeMapper;
        this.reviewMapper = reviewMapper;
        this.productUpdateMapper = productUpdateMapper;
    }

    @Override
    public ProductDTO createProduct(ProductCreateDTO productDTO) {
        // Convert the ProductCreateDTO to a Product entity
        Product product = productCreateMapper.toEntity(productDTO);

        // Check if a product with the same name already exists
        if (productRepository.existsByName(product.getName())) {
            throw new ProductNotFoundException("Product with the same name already exists");
        }

        // Check if the product's brand is provided
        if (product.getBrand() != null) {
            product.setBrand(brandMapper.toEntity(productDTO.getBrand()));
        }

        // Check if the product's images are provided
        if (productDTO.getImages() != null) {
            product.setImages(new HashSet<>(productDTO.getImages().stream()
                    .map(imageMapper::toEntity)
                    .collect(java.util.stream.Collectors.toSet())));
        }

        // Check if the product's variants are provided
        if (productDTO.getVariants() != null) {
            product.setVariants(productDTO.getVariants().stream()
                    .map(variantMapper::toEntity)
                    .collect(java.util.stream.Collectors.toSet()));
        }

        // Check if the product's attributes are provided
        if (productDTO.getAttributes() != null) {
            product.setAttributes(productDTO.getAttributes().stream()
                    .map(attributeMapper::toEntity)
                    .collect(java.util.stream.Collectors.toSet()));
        }

        // Check if the product's reviews are provided
        if (productDTO.getReviews() != null) {
            product.setReviews(productDTO.getReviews().stream()
                    .map(reviewMapper::toEntity)
                    .collect(java.util.stream.Collectors.toSet()));
        }

        // Set the creation and update timestamps to the current time
        LocalDateTime now = LocalDateTime.now();
        product.setCreatedAt(now);
        product.setUpdatedAt(now);

        // Save the new product to the repository
        product = productRepository.save(product);

        // Convert the saved product entity back to a ProductDTO and return it
        return productMapper.toDto(product);
    }

    @Override
    public Optional<ProductDTO> findProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        // Check if the product exists
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        return product.map(productMapper::toDto);
    }

    @Override
    public Optional<ProductDTO> findProductByName(String name) {
        // Find the product by name, throw an exception if not found
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with name: " + name));

        // Convert the Product entity to a ProductDTO and return it
        return Optional.of(productMapper.toDto(product));
    }

    @Override
    public Page<ProductDTO> findAllProducts(Pageable pageable) {
        // Fetch all Product entities with pagination
        Page<Product> productPage = productRepository.findAll(pageable);

        // Convert the Page<Product> to Page<ProductDTO> using the productMapper
        return productPage.map(productMapper::toDto);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO) {
        // Find the product by id, throw an exception if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // Update the product details with the new data from productUpdateDTO
        productUpdateMapper.updateEntity(productUpdateDTO, product);

        // Save the updated product to the repository
        product = productRepository.save(product);

        // Convert the updated Product entity to a ProductDTO using the productMapper and return it
        return productMapper.toDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        // Find the product by id, throw an exception if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // Delete the product from the repository
        productRepository.delete(product);
    }
}

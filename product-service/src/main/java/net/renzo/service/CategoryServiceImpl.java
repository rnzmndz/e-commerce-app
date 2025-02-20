package net.renzo.service;

import net.renzo.dto.CategoryDTO;
import net.renzo.exception.CategoryNotFoundException;
import net.renzo.exception.ProductNotFoundException;
import net.renzo.mapper.CategoryMapper;
import net.renzo.model.Category;
import net.renzo.model.Product;
import net.renzo.repository.CategoryRepository;
import net.renzo.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper
            , ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // Convert CategoryDTO to Category entity
        Category category = categoryMapper.toEntity(categoryDTO);

        // Save the Category entity to the repository
        category = categoryRepository.save(category);

        // Convert the saved Category entity back to CategoryDTO
        return categoryMapper.toDto(category);
    }

    @Override
    public Optional<CategoryDTO> findCategoryById(Long id) {
        // Find the category by ID, throw exception if not found
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        // Convert the found Category entity to CategoryDTO and return it wrapped in an Optional
        return Optional.of(categoryMapper.toDto(category));
    }

    @Override
    public Optional<CategoryDTO> findCategoryByName(String name) {
        // Find the category by name, throw exception if not found
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        // Convert the found Category entity to CategoryDTO and return it wrapped in an Optional
        return Optional.of(categoryMapper.toDto(category));
    }

    @Override
    public Page<CategoryDTO> findAllCategories(Pageable pageable) {
        // Retrieve all categories from the repository with pagination
        return categoryRepository.findAll(pageable)
                // Convert each Category entity to CategoryDTO
                .map(categoryMapper::toDto);
    }

    @Override
    @Transactional
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        // Find the existing category by ID, throw exception if not found
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        // Update the existing category with the new data
        categoryMapper.updateEntity(categoryDTO, existingCategory);

        // Save the updated category to the repository
        Category updatedCategory = categoryRepository.save(existingCategory);

        // Convert the updated category entity back to CategoryDTO
        return categoryMapper.toDto(updatedCategory);
    }
    @Override
    @Transactional
    public void addProductToCategory(Long categoryId, Long productId) {
        // Find the category by ID, throw exception if not found
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        // Find the product by ID, throw exception if not found
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        // Add the product to the category (assuming a method addProduct exists in Category)
        category.addProduct(product);

        // Save the updated category to the repository
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void removeProductFromCategory(Long categoryId, Long productId) {
        // Find the category by ID, throw exception if not found
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        // Find the product by ID, throw exception if not found
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        // Remove the product from the category (assuming a method removeProduct exists in Category)
        category.removeProduct(product);

        // Save the updated category to the repository
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        // Find the category by ID, throw exception if not found
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        // Delete the category from the repository
        categoryRepository.delete(category);
    }
}

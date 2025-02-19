package net.renzo.service;

import net.renzo.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing categories.
 */
public interface CategoryService {

    /**
     * Creates a new category.
     *
     * @param category the category to create
     * @return the created category
     */
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    /**
     * Finds a category by its ID.
     *
     * @param id the ID of the category
     * @return an Optional containing the found category, or empty if not found
     */
    Optional<CategoryDTO> findCategoryById(Long id);

    /**
     * Finds a category by its name.
     *
     * @param name the name of the category
     * @return an Optional containing the found category, or empty if not found
     */
    Optional<CategoryDTO> findCategoryByName(String name);

    /**
     * Finds all categories.
     *
     * @return a list of all categories
     */
    Page<CategoryDTO> findAllCategories(Pageable pageable);

    /**
     * Updates an existing category.
     *
     * @param id the ID of the category to update
     * @param category the updated category data
     * @return the updated category
     */
    CategoryDTO updateCategory(Long id, CategoryDTO category);

    /**
     * Adds a product to a category.
     *
     * @param categoryId the ID of the category
     * @param productId the ID of the product to add
     */
    void addProductToCategory(Long categoryId, Long productId);

    /**
     * Removes a product from a category.
     *
     * @param categoryId the ID of the category
     * @param productId the ID of the product to remove
     */
    void removeProductFromCategory(Long categoryId, Long productId);

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to delete
     */
    void deleteCategory(Long id);
}
package net.renzo.service;

import net.renzo.dto.CategoryDTO;
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
    CategoryDTO createCategory(CategoryDTO category);

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
    List<CategoryDTO> findAllCategories();

    /**
     * Updates an existing category.
     *
     * @param id the ID of the category to update
     * @param category the updated category data
     * @return the updated category
     */
    CategoryDTO updateCategory(Long id, CategoryDTO category);

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to delete
     */
    void deleteCategory(Long id);
}
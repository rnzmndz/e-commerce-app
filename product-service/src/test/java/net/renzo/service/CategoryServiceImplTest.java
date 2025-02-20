package net.renzo.service;

import net.renzo.dto.CategoryDTO;
import net.renzo.mapper.CategoryMapper;
import net.renzo.model.Category;
import net.renzo.model.Product;
import net.renzo.repository.CategoryRepository;
import net.renzo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private static final Long CATEGORY_ID = 1L;
    private static final String CATEGORY_NAME = "Electronics";
    private static final Long PRODUCT_ID = 1L;

    private Category category;
    private CategoryDTO categoryDTO;
    private Product product;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(CATEGORY_ID);
        category.setName(CATEGORY_NAME);

        categoryDTO = new CategoryDTO();
        categoryDTO.setId(CATEGORY_ID);
        categoryDTO.setName(CATEGORY_NAME);

        product = new Product();
        product.setId(PRODUCT_ID);
    }

    @Test
    void createCategory() {
        // Arrange
        when(categoryMapper.toEntity(categoryDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(categoryDTO);

        // Act
        CategoryDTO result = categoryService.createCategory(categoryDTO);

        // Assert
        assertNotNull(result);
        assertEquals(CATEGORY_ID, result.getId());
        assertEquals(CATEGORY_NAME, result.getName());
        verify(categoryMapper).toEntity(categoryDTO);
        verify(categoryRepository).save(category);
        verify(categoryMapper).toDto(category);
    }

    @Test
    void findCategoryById() {
        // Arrange
        when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(categoryDTO);

        // Act
        Optional<CategoryDTO> result = categoryService.findCategoryById(CATEGORY_ID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(CATEGORY_ID, result.get().getId());
        assertEquals(CATEGORY_NAME, result.get().getName());
        verify(categoryRepository).findById(CATEGORY_ID);
        verify(categoryMapper).toDto(category);
    }

    @Test
    void findCategoryByName() {
        // Arrange
        when(categoryRepository.findByName(CATEGORY_NAME)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(categoryDTO);

        // Act
        Optional<CategoryDTO> result = categoryService.findCategoryByName(CATEGORY_NAME);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(CATEGORY_ID, result.get().getId());
        assertEquals(CATEGORY_NAME, result.get().getName());
        verify(categoryRepository).findByName(CATEGORY_NAME);
        verify(categoryMapper).toDto(category);
    }

    @Test
    void findAllCategories() {
        // Arrange
        Pageable pageable = Pageable.unpaged();
        Page<Category> categoryPage = new PageImpl<>(List.of(category));
        when(categoryRepository.findAll(pageable)).thenReturn(categoryPage);
        when(categoryMapper.toDto(category)).thenReturn(categoryDTO);

        // Act
        Page<CategoryDTO> result = categoryService.findAllCategories(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(CATEGORY_ID, result.getContent().get(0).getId());
        assertEquals(CATEGORY_NAME, result.getContent().get(0).getName());
        verify(categoryRepository).findAll(pageable);
        verify(categoryMapper).toDto(category);
    }

   @Test
    void updateCategory() {
        // Arrange
        when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(categoryDTO);
        when(categoryRepository.save(category)).thenReturn(category);

        // Act
        CategoryDTO result = categoryService.updateCategory(CATEGORY_ID, categoryDTO);

        // Assert
        assertNotNull(result);
        assertEquals(CATEGORY_ID, result.getId());
        assertEquals(CATEGORY_NAME, result.getName());
        verify(categoryRepository).findById(CATEGORY_ID);
        verify(categoryMapper).updateEntity(categoryDTO, category);
        verify(categoryRepository).save(category);
        verify(categoryMapper).toDto(category);
    }

    @Test
    void addProductToCategory() {
        // Arrange
        when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.of(category));
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
        // Act
        categoryService.addProductToCategory(CATEGORY_ID, PRODUCT_ID);
        // Assert
        verify(categoryRepository).findById(CATEGORY_ID);
        verify(categoryRepository).save(category);
        // Add additional verifications as needed
    }

    @Test
    void removeProductFromCategory() {
        // Arrange
        when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.of(category));
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
        // Act
        categoryService.removeProductFromCategory(CATEGORY_ID, PRODUCT_ID);
        // Assert
        verify(categoryRepository).findById(CATEGORY_ID);
        // Add additional verifications as needed
    }

    @Test
    void deleteCategory() {
        // Arrange
        when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.of(category));

        // Act
        categoryService.deleteCategory(CATEGORY_ID);

        // Assert
        verify(categoryRepository).findById(CATEGORY_ID);
        verify(categoryRepository).delete(category);
    }
}
package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;
import net.renzo.dto.CategoryDTO;
import net.renzo.model.Category;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CategoryMapperTest {

    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    void testToCategoryDTO() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        CategoryDTO categoryDTO = mapper.toDto(category);

        assertNotNull(categoryDTO);
        assertEquals(1L, categoryDTO.getId());
        assertEquals("Electronics", categoryDTO.getName());
    }

    @Test
    void testToCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Electronics");

        Category category = mapper.toEntity(categoryDTO);

        assertNotNull(category);
        assertEquals(1L, category.getId());
        assertEquals("Electronics", category.getName());
    }
}
package net.renzo.mapper;

import net.renzo.dto.CategoryDTO;
import net.renzo.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(Category category);

    Category toEntity(CategoryDTO categoryDTO);

    void updateEntity(CategoryDTO categoryDTO, @MappingTarget Category category);
}

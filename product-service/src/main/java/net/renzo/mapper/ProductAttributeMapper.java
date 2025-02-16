package net.renzo.mapper;

import net.renzo.dto.ProductAttributeDTO;
import net.renzo.model.ProductAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapper {

    ProductAttributeDTO toDto(ProductAttribute productAttribute);

    ProductAttribute toEntity(ProductAttributeDTO productAttributeDTO);

    void updateEntity(ProductAttributeDTO productAttributeDTO, @MappingTarget ProductAttribute productAttribute);
}
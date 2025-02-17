package net.renzo.mapper;

import net.renzo.dto.AttributeDTO;
import net.renzo.model.Attribute;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapper {

    AttributeDTO toDto(Attribute attribute);

    Attribute toEntity(AttributeDTO attributeDTO);

    void updateEntity(AttributeDTO attributeDTO, @MappingTarget Attribute attribute);
}
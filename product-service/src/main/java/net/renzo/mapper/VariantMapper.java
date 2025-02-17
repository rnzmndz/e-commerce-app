package net.renzo.mapper;

import net.renzo.dto.VariantDTO;
import net.renzo.model.Variant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VariantMapper {

    VariantDTO toDto(Variant variant);

    Variant toEntity(VariantDTO variantDTO);

    void updateEntity(VariantDTO variantDTO, @MappingTarget Variant variant);
}

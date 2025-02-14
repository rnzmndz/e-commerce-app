package net.renzo.mapper;

import net.renzo.dto.ProductVariantDTO;
import net.renzo.model.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {

    ProductVariantDTO toProductVariantDTO(ProductVariant productVariant);

    ProductVariant toProductVariant(ProductVariantDTO productVariantDTO);

    void updateEntity(ProductVariantDTO productVariantDTO, @MappingTarget ProductVariant productVariant);
}

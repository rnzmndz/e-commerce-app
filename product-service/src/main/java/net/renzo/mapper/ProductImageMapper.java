package net.renzo.mapper;

import net.renzo.dto.ProductImageDTO;
import net.renzo.model.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    ProductImageDTO toDto(ProductImage productImage);

    ProductImage toEntity(ProductImageDTO productImageDTO);


    void updateEntity(ProductImageDTO productImageDTO, @MappingTarget ProductImage productImage);
}
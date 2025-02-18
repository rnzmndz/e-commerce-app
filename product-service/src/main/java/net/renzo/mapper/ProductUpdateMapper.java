package net.renzo.mapper;

import net.renzo.dto.ProductUpdateDTO;
import net.renzo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

// TODO Comeback to this later
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductUpdateMapper {

    ProductUpdateDTO toDto(Product product);

    Product toEntity(ProductUpdateDTO productUpdateDTO);

    void updateEntity(ProductUpdateDTO productUpdateDTO, @MappingTarget Product product);
}
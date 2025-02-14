package net.renzo.mapper;

import net.renzo.dto.ProductUpdateDTO;
import net.renzo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

// TODO Comeback to this later
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductUpdateMapper {

    @Mapping(source = "name", target = "productName")
    @Mapping(source = "description", target = "productDescription")
    ProductUpdateDTO toDto(Product product);

    @Mapping(source = "productName", target = "name")
    @Mapping(source = "productDescription", target = "description")
    Product toEntity(ProductUpdateDTO productUpdateDTO);
}
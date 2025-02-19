package net.renzo.mapper;

import net.renzo.dto.ProductDTO;
import net.renzo.dto.ProductDetailDTO;
import net.renzo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {ProductImageMapper.class,
                ProductAttributeMapper.class,
                ProductReviewMapper.class},
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(source = "brand.name", target = "brandName")
    @Mapping(source = "defaultImage.url", target = "defaultImage")
    ProductDTO toDto(Product product);

    @Mapping(source = "brandName", target = "brand.name")
    @Mapping(source = "defaultImage", target = "defaultImage.url")
    Product toEntity(ProductDTO productDetailDTO);
}
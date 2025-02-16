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

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "brand.name", target = "brandName")
    ProductDTO toDto(Product product);

    @Mapping(source = "categoryName", target = "category.name")
    @Mapping(source = "brandName", target = "brand.name")
    Product toEntity(ProductDTO productDetailDTO);
}
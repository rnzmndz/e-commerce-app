package net.renzo.mapper;

import net.renzo.dto.ProductListDTO;
import net.renzo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {ProductImageMapper.class,
                ProductAttributeMapper.class,
                ProductReviewMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductListMapper {

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "brand.name", target = "brandName")
    ProductListDTO toProductListDTO(Product product);

    @Mapping(source = "categoryName", target = "category.name")
    @Mapping(source = "brandName", target = "brand.name")
    Product toProduct(ProductListDTO productListDTO);
}

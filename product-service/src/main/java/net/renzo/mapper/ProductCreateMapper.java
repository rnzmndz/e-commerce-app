package net.renzo.mapper;

import net.renzo.dto.ProductCreateDTO;
import net.renzo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {CategoryMapper.class,
        BrandMapper.class,
        ProductImageMapper.class,
        ProductVariantMapper.class,
        ProductAttributeMapper.class,
        ProductReviewMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCreateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "productImages", source = "images")
    @Mapping(target = "productVariants", source = "variants")
    @Mapping(target = "productAttributes", source = "attributes")
    @Mapping(target = "productReviews", source = "reviews")
    Product toEntity(ProductCreateDTO productCreateDTO);

}
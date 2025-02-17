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
        VariantMapper.class,
        ProductAttributeMapper.class,
        ProductReviewMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCreateMapper {

    @Mapping(target = "category", source = "category")
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "images", source = "images")
    @Mapping(target = "variants", source = "variants")
    @Mapping(target = "attributes", source = "attributes")
    @Mapping(target = "reviews", source = "reviews")
    Product toEntity(ProductCreateDTO productCreateDTO);

}
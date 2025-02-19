package net.renzo.mapper;

import net.renzo.dto.ProductDetailDTO;
import net.renzo.model.Brand;
import net.renzo.model.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {ProductImageMapper.class,
                ProductAttributeMapper.class,
                ProductReviewMapper.class,
                BrandMapper.class,
                CategoryMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDetailMapper {

    @Mapping(source = "brand.name", target = "brandName")
    ProductDetailDTO toDto(Product product);

    @Mapping(source = "brandName", target = "brand", qualifiedByName = "toBrand")
    Product toEntity(ProductDetailDTO productDetailDTO);

    @Named("toBrand")
    default Brand toBrand(String brandName) {
        if (brandName == null) {
            return null;
        }
        return Brand.builder()
                .name(brandName)
                .build();
    }
}
package net.renzo.mapper;

import net.renzo.dto.ProductReviewDTO;
import net.renzo.model.ProductReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = {UserDetailMapper.class})
public interface ProductReviewMapper {

    @Mapping(source = "userDetail.id", target = "userId")
    ProductReviewDTO toDto(ProductReview productReview);

    @Mapping(source = "userId", target = "userDetail.id")
    ProductReview toEntity(ProductReviewDTO productReviewDTO);

    void updateEntity(ProductReviewDTO productReviewDTO, @MappingTarget ProductReview productReview);
}
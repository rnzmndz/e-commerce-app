package net.renzo.mapper;

import net.renzo.dto.ReviewDTO;
import net.renzo.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = {UserDetailMapper.class})
public interface ProductReviewMapper {

    @Mapping(source = "userDetail.id", target = "userId")
    ReviewDTO toDto(Review review);

    @Mapping(source = "userId", target = "userDetail.id")
    Review toEntity(ReviewDTO reviewDTO);

    void updateEntity(ReviewDTO reviewDTO, @MappingTarget Review review);
}
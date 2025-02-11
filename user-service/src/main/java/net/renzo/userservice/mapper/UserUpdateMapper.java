package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.UserUpdateDTO;
import net.renzo.userservice.model.UserDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {AddressMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserUpdateMapper {
    UserUpdateMapper INSTANCE = Mappers.getMapper(UserUpdateMapper.class);

    @Mapping(target = "addresses", source = "address")
    UserDetail toEntity(UserUpdateDTO userUpdateDTO);

    void updateEntityFromDto(UserUpdateDTO userUpdateDTO, @MappingTarget UserDetail userDetail);

}

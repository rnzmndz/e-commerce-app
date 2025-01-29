package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.model.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {ProfileMapper.class, AddressMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserCreateMapper {
    UserCreateMapper INSTANCE = Mappers.getMapper(UserCreateMapper.class);

    UserDetail toEntity(UserCreateDTO userCreateDTO);
}

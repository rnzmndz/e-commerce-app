package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.model.Address;
import net.renzo.userservice.model.UserDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ProfileMapper.class,
                AddressMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserCreateMapper {
    UserCreateMapper INSTANCE = Mappers.getMapper(UserCreateMapper.class);

    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "profile", ignore = true)
    UserDetail toEntity(UserCreateDTO dto);

}

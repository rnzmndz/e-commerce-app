package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.UserCredentialsDTO;
import net.renzo.userservice.model.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserCredentialsMapper {
    UserCredentialsMapper INSTANCE = Mappers.getMapper(UserCredentialsMapper.class);

    UserCredentialsDTO toDTO(UserDetail userDetail);
}

package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.UserCredentialsDTO;
import net.renzo.userservice.model.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserCredentialsMapper {
    UserCredentialsDTO toDTO(UserDetail userDetail);

    UserDetail toEntity(UserCredentialsDTO userCredentialsDTO);
}

package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.UserListDTO;
import net.renzo.userservice.model.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserListMapper {

    UserListMapper INSTANCE = Mappers.getMapper(UserListMapper.class);

    UserListDTO toDTO(UserDetail userDetail);
    List<UserListDTO> toDTOList(List<UserDetail> users);
}

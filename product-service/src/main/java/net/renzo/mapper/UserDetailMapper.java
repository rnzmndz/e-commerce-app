package net.renzo.mapper;

import net.renzo.model.UserDetail;
import net.renzo.dto.UserDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserDetailMapper {

    UserDetailDTO toUserDetailDTO(UserDetail userDetail);

    UserDetail toUserDetail(UserDetailDTO userDetailDTO);

    void updateEntity(UserDetailDTO userDetailDTO, @MappingTarget UserDetail userDetail);
}
package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.UserBasicInfoDTO;
import net.renzo.userservice.dto.UserDTO;
import net.renzo.userservice.model.UserDetail;
import org.mapstruct.Mapper;

// TODO make a test file for this
@Mapper(componentModel = "spring")
public interface UserBasicInfoMapper {

    UserBasicInfoDTO toUserBasicInfoDTO(UserDetail userDetail);
}

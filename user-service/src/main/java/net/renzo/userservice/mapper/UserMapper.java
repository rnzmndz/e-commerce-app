package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.dto.UserDTO;
import net.renzo.userservice.model.Authority;
import net.renzo.userservice.model.UserDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = AddressMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "authorities", expression = "java(mapAuthorities(userDetail))")
    UserDTO toDTO(UserDetail userDetail);


    default Set<String> mapAuthorities(UserDetail userDetail) {
        if (userDetail == null) {
            return null;
        }
        Set<String> authorities = userDetail.getAuthorityName();
        if (authorities == null) {
            return null;
        }
        return authorities.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

}

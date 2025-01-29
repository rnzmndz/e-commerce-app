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

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "authorities", expression = "java(mapAuthorities(userDetail))")
    @Mapping(target = "addresses", expression = "java(mapAddresses(userDetail))")
    UserDTO toDTO(UserDetail userDetail);

//    @InheritInverseConfiguration
//    @Mapping(target = "addresses", expression = "java(userDTO.getAddress() != null ? java.util.Collections.singletonList(userDTO.getAddress()) : java.util.Collections.emptyList())")
//    @Mapping(target = "authorities", ignore = true)
//    UserDetail toEntity(UserDTO userDTO);
//
//    @AfterMapping
//    default void setAuthorities(@MappingTarget UserDetail userDetail, UserDTO userDTO) {
//        if (userDTO.getAuthorities() != null) {
//            userDTO.getAuthorities().forEach(auth -> {
//                Authority authority = Authority.builder()
//                        .name(auth)
//                        .build();
//                userDetail.addAuthorities(authority);
//            });
//        }
//    }

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

    default List<AddressDTO> mapAddresses(UserDetail userDetail) {
        return userDetail.getAddresses().stream()
                .map(address -> Mappers.getMapper(AddressMapper.class).INSTANCE.toDTO(address))
                .collect(Collectors.toList());
    }
//    void updateEntityFromDto(UserDTO userDTO, @MappingTarget UserDetail userDetail);
}

package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.dto.UserUpdateDTO;
import net.renzo.userservice.model.Address;
import net.renzo.userservice.model.UserDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

//TODO Fix this mapper
@Mapper(componentModel = "spring",
        uses = {AddressMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserUpdateMapper {
    UserUpdateMapper INSTANCE = Mappers.getMapper(UserUpdateMapper.class);

    @Mapping(target = "addresses", source = "address"/*, qualifiedByName = "mapSingleAddressToList"*/)
    UserDetail toEntity(UserUpdateDTO userUpdateDTO);

    void updateEntityFromDto(UserUpdateDTO userUpdateDTO, @MappingTarget UserDetail userDetail);

//    @Named("mapSingleAddressToList")
//    default List<Address> mapSingleAddressToList(AddressDTO addressDTO, @Context AddressMapper addressMapper) {
//        if (addressDTO == null) {
//            return Collections.emptyList();
//        }
//        return Collections.singletonList(addressMapper.toEntity(addressDTO));
//    }
}

package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.model.Address;
import net.renzo.userservice.model.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ProfileMapper.class,
                AddressMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserCreateMapper {
    UserCreateMapper INSTANCE = Mappers.getMapper(UserCreateMapper.class);

    @Mapping(target = "addresses", source = "address", qualifiedByName = "mapSingleAddressToList")
    UserDetail toEntity(UserCreateDTO userCreateDTO);

    @Named("mapSingleAddressToList")
    default List<Address> mapSingleAddressToList(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(AddressMapper.INSTANCE.toEntity(addressDTO));
    }
}

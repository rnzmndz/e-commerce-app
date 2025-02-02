package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.model.Address;
import net.renzo.userservice.model.UserDetail;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ProfileMapper.class,
                AddressMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserCreateMapper {

    @Mapping(target = "addresses", source = "address", qualifiedByName = "mapSingleAddressToList")
    UserDetail toEntity(UserCreateDTO userCreateDTO, @Context AddressMapper addressMapper);

    @Named("mapSingleAddressToList")
    default List<Address> mapSingleAddressToList(AddressDTO addressDTO, @Context AddressMapper addressMapper) {
        if (addressDTO == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(addressMapper.toEntity(addressDTO));
    }
}

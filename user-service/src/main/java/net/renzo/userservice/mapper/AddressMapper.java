package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

    AddressDTO toDTO(Address address);

    Address toEntity(AddressDTO addressDTO);
}

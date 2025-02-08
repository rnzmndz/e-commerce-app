package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.AuthorityDTO;
import net.renzo.userservice.model.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorityMapper {

    AuthorityDTO toDTO(Authority authority);

    Authority toEntity(AuthorityDTO authorityDTO);

    void updateFromDTO(AuthorityDTO authorityDTO, @MappingTarget Authority authority);
}

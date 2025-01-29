package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.ProfileDTO;
import net.renzo.userservice.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ProfileDTO toDTO(Profile profile);

    Profile toEntity(ProfileDTO profileDTO);
}
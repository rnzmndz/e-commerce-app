package net.renzo.mapper;

import net.renzo.dto.BrandDTO;
import net.renzo.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BrandMapper {

    BrandDTO toBrandDTO(Brand brand);

    Brand toBrand(BrandDTO brandDTO);

    void updateEntity(BrandDTO brandDTO, @MappingTarget Brand brand);
}
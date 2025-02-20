package net.renzo.mapper;

import net.renzo.dto.ImageDTO;
import net.renzo.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    ImageDTO toDto(Image image);

    Image toEntity(ImageDTO imageDTO);

    void updateEntity(ImageDTO imageDTO, @MappingTarget Image image);
}
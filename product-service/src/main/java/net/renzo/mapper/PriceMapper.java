package net.renzo.mapper;

import net.renzo.dto.PriceDTO;
import net.renzo.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceDTO toDto(Price price);

    Price toEntity(PriceDTO priceDTO);

    void updateEntity(PriceDTO priceDTO, @MappingTarget Price price);
}
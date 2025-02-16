package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import net.renzo.dto.PriceDTO;
import net.renzo.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class PriceMapperTest {

    private PriceMapper priceMapper;

    @BeforeEach
    void setUp() {
        priceMapper = Mappers.getMapper(PriceMapper.class);
    }

    @Test
    void testToDTO() {
        Price price = new Price();
        price.setId(1L);
        price.setAmount(100.0);

        PriceDTO priceDTO = priceMapper.toDto(price);

        assertNotNull(priceDTO);
        assertEquals(1L, priceDTO.getId());
        assertEquals(100.0, priceDTO.getAmount());
    }

    @Test
    void testToEntity() {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(1L);
        priceDTO.setAmount(100.0);

        Price price = priceMapper.toEntity(priceDTO);

        assertNotNull(price);
        assertEquals(1L, price.getId());
        assertEquals(100.0, price.getAmount());
    }

    @Test
    void testUpdateEntity() {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(1L);
        priceDTO.setAmount(200.0);

        Price price = new Price();
        price.setId(1L);
        price.setAmount(100.0);

        priceMapper.updateEntity(priceDTO, price);

        assertEquals(1L, price.getId());
        assertEquals(200.0, price.getAmount());
    }
}
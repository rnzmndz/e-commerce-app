package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.model.Address;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class AddressMapperTest {

    private final AddressMapper addressMapper =  Mappers.getMapper(AddressMapper.class);

    @Test
    void toDTO() {
        // Arrange
        Address address = Address.builder()
                .id(1L)
                .street("123 Main St")
                .city("Anytown")
                .state("Anystate")
                .zipCode("12345")
                .build();

        // Act
        AddressDTO addressDTO = addressMapper.toDTO(address);

        // Assert
        assertThat(addressDTO.getId()).isEqualTo(address.getId());
        assertThat(addressDTO.getStreet()).isEqualTo(address.getStreet());
        assertThat(addressDTO.getCity()).isEqualTo(address.getCity());
        assertThat(addressDTO.getState()).isEqualTo(address.getState());
        assertThat(addressDTO.getZipCode()).isEqualTo(address.getZipCode());
    }

    @Test
    void toEntity() {
        // Arrange
        AddressDTO addressDTO = AddressDTO.builder()
                .id(1L)
                .street("123 Main St")
                .city("Anytown")
                .state("Anystate")
                .zipCode("12345")
                .build();

        // Act
        Address address = addressMapper.toEntity(addressDTO);

        // Assert
        assertThat(address.getId()).isEqualTo(addressDTO.getId());
        assertThat(address.getStreet()).isEqualTo(addressDTO.getStreet());
        assertThat(address.getCity()).isEqualTo(addressDTO.getCity());
        assertThat(address.getState()).isEqualTo(addressDTO.getState());
        assertThat(address.getZipCode()).isEqualTo(addressDTO.getZipCode());
    }
}
package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.UserCredentialsDTO;
import net.renzo.userservice.model.UserDetail;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class UserCredentialsMapperTest {

    private final UserCredentialsMapper userCredentialsMapper = Mappers.getMapper(UserCredentialsMapper.class);

    @Test
    void toDTO() {
        // Arrange
        UserDetail userDetail = UserDetail.builder()
                .id(1L)
                .username("testuser")
                .password("password")
                .build();

        // Act
        UserCredentialsDTO userCredentialsDTO = userCredentialsMapper.toDTO(userDetail);

        // Assert
        assertThat(userCredentialsDTO.getId()).isEqualTo(userDetail.getId());
        assertThat(userCredentialsDTO.getUsername()).isEqualTo(userDetail.getUsername());
        assertThat(userCredentialsDTO.getPassword()).isEqualTo(userDetail.getPassword());
    }
}
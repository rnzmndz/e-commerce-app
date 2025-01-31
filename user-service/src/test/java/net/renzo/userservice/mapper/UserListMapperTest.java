package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.UserListDTO;
import net.renzo.userservice.model.UserDetail;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserListMapperTest {

    private final UserListMapper userListMapper = Mappers.getMapper(UserListMapper.class);

    @Test
    void toDTO() {
        // Arrange
        UserDetail userDetail = UserDetail.builder()
                .id(1L)
                .username("testuser")
                .email("testuser@example.com")
                .firstName("Test")
                .lastName("User")
                .build();

        // Act
        UserListDTO userListDTO = userListMapper.toDTO(userDetail);

        // Assert
        assertThat(userListDTO.getId()).isEqualTo(userDetail.getId());
        assertThat(userListDTO.getUsername()).isEqualTo(userDetail.getUsername());
        assertThat(userListDTO.getEmail()).isEqualTo(userDetail.getEmail());
        assertThat(userListDTO.getFirstName()).isEqualTo(userDetail.getFirstName());
        assertThat(userListDTO.getLastName()).isEqualTo(userDetail.getLastName());
    }

    @Test
    void toDTOList() {
        // Arrange
        UserDetail userDetail1 = UserDetail.builder()
                .id(1L)
                .username("testuser1")
                .email("testuser1@example.com")
                .firstName("Test1")
                .lastName("User1")
                .build();

        UserDetail userDetail2 = UserDetail.builder()
                .id(2L)
                .username("testuser2")
                .email("testuser2@example.com")
                .firstName("Test2")
                .lastName("User2")
                .build();

        List<UserDetail> userDetailList = Arrays.asList(userDetail1, userDetail2);

        // Act
        List<UserListDTO> userListDTOList = userListMapper.toDTOList(userDetailList);

        // Assert
        assertThat(userListDTOList).hasSize(2);
        assertThat(userListDTOList.get(0).getId()).isEqualTo(userDetail1.getId());
        assertThat(userListDTOList.get(0).getUsername()).isEqualTo(userDetail1.getUsername());
        assertThat(userListDTOList.get(0).getEmail()).isEqualTo(userDetail1.getEmail());
        assertThat(userListDTOList.get(0).getFirstName()).isEqualTo(userDetail1.getFirstName());
        assertThat(userListDTOList.get(0).getLastName()).isEqualTo(userDetail1.getLastName());

        assertThat(userListDTOList.get(1).getId()).isEqualTo(userDetail2.getId());
        assertThat(userListDTOList.get(1).getUsername()).isEqualTo(userDetail2.getUsername());
        assertThat(userListDTOList.get(1).getEmail()).isEqualTo(userDetail2.getEmail());
        assertThat(userListDTOList.get(1).getFirstName()).isEqualTo(userDetail2.getFirstName());
        assertThat(userListDTOList.get(1).getLastName()).isEqualTo(userDetail2.getLastName());
    }
}
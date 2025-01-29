package net.renzo.userservice.mapper;

import net.renzo.userservice.model.Authority;
import net.renzo.userservice.model.UserDetail;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private final UserMapper userMapper = UserMapper.INSTANCE;

//    @Test
//    void testToDTO() {
//        // Arrange
//        UserDetail userDetail = new UserDetail();
//        userDetail.setId(1L);
//        userDetail.setUsername("john_doe");
//        userDetail.setEmail("john.doe@example.com");
//
//        // Add authorities
//        Authority authority = new Authority();
//        authority.setName("ROLE_USER");
//        userDetail.setAuthorities(Collections.singleton(authority));
//
//        // Add address
//        userDetail.setAddresses(Collections.singletonList("123 Main St"));
//
//        // Act
//        UserDTO userDTO = userMapper.toDTO(userDetail);
//
//        // Assert
//        assertThat(userDTO).isNotNull();
//        assertThat(userDTO.getId()).isEqualTo(1L);
//        assertThat(userDTO.getUsername()).isEqualTo("john_doe");
//        assertThat(userDTO.getEmail()).isEqualTo("john.doe@example.com");
//        assertThat(userDTO.getAuthorities()).containsExactly("ROLE_USER");
//        assertThat(userDTO.getAddress()).isEqualTo("123 Main St");
//    }
//
//    @Test
//    void testToDTO_WithNullAuthorities() {
//        // Arrange
//        UserDetail userDetail = new UserDetail();
//        userDetail.setId(1L);
//        userDetail.setUsername("john_doe");
//        userDetail.setEmail("john.doe@example.com");
//        userDetail.setAuthorities(null); // No authorities
//        userDetail.setAddresses(Collections.singletonList("123 Main St"));
//
//        // Act
//        UserDTO userDTO = userMapper.toDTO(userDetail);
//
//        // Assert
//        assertThat(userDTO).isNotNull();
//        assertThat(userDTO.getAuthorities()).isNull(); // Authorities should be null
//    }
//
//    @Test
//    void testToDTO_WithEmptyAddresses() {
//        // Arrange
//        UserDetail userDetail = new UserDetail();
//        userDetail.setId(1L);
//        userDetail.setUsername("john_doe");
//        userDetail.setEmail("john.doe@example.com");
//        userDetail.setAuthorities(Collections.singleton(new Authority("ROLE_USER")));
//        userDetail.setAddresses(Collections.emptyList()); // Empty addresses
//
//        // Act
//        UserDTO userDTO = userMapper.toDTO(userDetail);
//
//        // Assert
//        assertThat(userDTO).isNotNull();
//        assertThat(userDTO.getAddress()).isNull(); // Address should be null
//    }
//
    @Test
    void testMapAuthorities() {
        // Arrange
        UserDetail userDetail = new UserDetail();
        Authority authority = new Authority();
        authority.setName("ROLE_ADMIN");
        userDetail.setAuthorities(Collections.singleton(authority));

        // Act
        Set<String> authorities = userMapper.mapAuthorities(userDetail);

        // Assert
        assertThat(authorities).isNotNull();
        assertThat(authorities).containsExactly("ROLE_ADMIN");
    }



}
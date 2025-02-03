package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.UserDTO;
import net.renzo.userservice.model.Authority;
import net.renzo.userservice.model.UserDetail;
import net.renzo.userservice.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private UserMapperImpl userMapper;

    private UserDetail userDetail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Authority authority1 = Authority.builder()
                .name("ROLE_USER")
                .build();
        Authority authority2 = Authority.builder()
                .name("ROLE_ADMIN")
                .build();

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority1);
        authorities.add(authority2);

        userDetail = UserDetail.builder()
                .id(1L)
                .username("john_doe")
                .password("password123")
                .email("john.doe@example.com")
                .authorities(authorities)
                .firstName("John")
                .lastName("Doe")
                .role(UserRole.CUSTOMER)
                .build();
    }

    @Test
    void testToDTO() {
        // Act
        UserDTO userDTO = userMapper.toDTO(userDetail);

        // Assert
        assertThat(userDTO).isNotNull();
        assertThat(userDTO.getId()).isEqualTo(userDetail.getId());
        assertThat(userDTO.getUsername()).isEqualTo(userDetail.getUsername());
        assertThat(userDTO.getEmail()).isEqualTo(userDetail.getEmail());
        assertThat(userDTO.getFirstName()).isEqualTo(userDetail.getFirstName());
        assertThat(userDTO.getLastName()).isEqualTo(userDetail.getLastName());
        assertThat(userDTO.getRole()).isEqualTo(userDetail.getRole());
        assertThat(userDTO.getAuthorities()).containsExactlyInAnyOrder("ROLE_USER", "ROLE_ADMIN");
    }

    @Test
    void testMapAuthorities_WithNullUserDetail() {
        // Act
        Set<String> authorities = userMapper.mapAuthorities(null);

        // Assert
        assertThat(authorities).isNull();
    }

    @Test
    void testMapAuthorities_WithNullAuthorities() {
        // Arrange
        userDetail.setAuthorities(null);

        // Act
        Set<String> authorities = userMapper.mapAuthorities(userDetail);

        // Assert
        assertThat(authorities).isNull();
    }

    @Test
    void testMapAuthorities_WithEmptyAuthorities() {
        // Arrange
        userDetail.setAuthorities(new HashSet<>());

        // Act
        Set<String> authorities = userMapper.mapAuthorities(userDetail);

        // Assert
        assertThat(authorities).isEmpty();
    }

    @Test
    void testMapAuthorities_WithValidAuthorities() {
        // Act
        Set<String> authorities = userMapper.mapAuthorities(userDetail);

        // Assert
        assertThat(authorities)
                .hasSize(2)
                .contains("ROLE_USER", "ROLE_ADMIN");
    }
}
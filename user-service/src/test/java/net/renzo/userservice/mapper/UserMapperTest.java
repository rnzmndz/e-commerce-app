package net.renzo.userservice.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import net.renzo.userservice.model.Authority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.renzo.userservice.model.UserDetail;

class UserMapperTest {
	private UserMapper userMapper;

	@BeforeEach
	void setUp() throws Exception {
        userMapper = UserMapper.INSTANCE;
	}

    @Test
    @DisplayName("Should return null when UserDetail is null")
    void testMapAuthorities_WithNullUserDetail() {
        // Act
        Set<String> authorities = userMapper.mapAuthorities(null);

        // Assert
        assertThat(authorities).isNull();
    }

    @Test
    @DisplayName("Should return null when authorities is null")
    void testMapAuthorities_WithNullAuthorities() {
        // Arrange
        UserDetail userDetail = new UserDetail();
        userDetail.addAuthorities(null);

        // Act
        Set<String> authorities = userMapper.mapAuthorities(userDetail);

        // Assert
        assertThat(authorities).isEmpty();
    }

    @Test
    @DisplayName("Should return empty set when authorities is empty")
    void testMapAuthorities_WithEmptyAuthorities() {
        // Arrange
        UserDetail userDetail = new UserDetail();
        userDetail.setAuthorities(new HashSet<>());

        // Act
        Set<String> authorities = userMapper.mapAuthorities(userDetail);

        // Assert
        assertThat(authorities).isEmpty();
    }

    @Test
    @DisplayName("Should map authorities correctly when authorities exist")
    void testMapAuthorities_WithValidAuthorities() {
        // Arrange
        UserDetail userDetail = new UserDetail();
        Set<Authority> authoritySet = new HashSet<>();

        Authority authority1 = Authority.builder()
                .name("ROLE_USER")
                .build();
        Authority authority2 = Authority.builder()
                .name("ROLE_ADMIN")
                .build();

        authoritySet.add(authority1);
        authoritySet.add(authority2);
        userDetail.setAuthorities(authoritySet);

        // Act
        Set<String> authorities = userMapper.mapAuthorities(userDetail);

        // Assert
        assertThat(authorities)
                .hasSize(2)
                .contains("ROLE_USER", "ROLE_ADMIN");
    }

    @Test
    @DisplayName("Should handle single authority mapping correctly")
    void testMapAuthorities_WithSingleAuthority() {
        // Arrange
        UserDetail userDetail = new UserDetail();
        Set<Authority> authoritySet = new HashSet<>();

        Authority authority = Authority.builder()
                .name("ROLE_USER")
                .build();

        authoritySet.add(authority);
        userDetail.setAuthorities(authoritySet);

        // Act
        Set<String> authorities = userMapper.mapAuthorities(userDetail);

        // Assert
        assertThat(authorities)
                .hasSize(1)
                .contains("ROLE_USER");
    }

}

import net.renzo.userservice.dto.UserUpdateDTO;
import net.renzo.userservice.mapper.AddressMapper;
import net.renzo.userservice.mapper.UserUpdateMapper;
import net.renzo.userservice.mapper.UserUpdateMapperImpl;
import net.renzo.userservice.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserUpdateMapperTest {

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private UserUpdateMapperImpl userUpdateMapper;

    private UserDetail userDetail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Address address = Address.builder()
                .id(1L)
                .street("123 Main St")
                .city("Springfield")
                .state("IL")
                .zipCode("62701")
                .build();

        Authority authority = Authority.builder()
                .id(1L)
                .name("ROLE_USER")
                .build();

        Set<Authority> authorities = Set.of(authority);

        Profile profile = Profile.builder()
                .id(1L)
                .profilePictureUrl("https://example.com/profile.jpg")
                .bio("This is a test bio")
                .build();

        userDetail = UserDetail.builder()
                .id(1L)
                .username("john_doe")
                .password("password123")
                .email("john.doe@example.com")
                .authorities(authorities)
                .firstName("John")
                .lastName("Doe")
                .role(UserRole.CUSTOMER)
                .addresses(address)
                .profile(profile)
                .build();
    }

    @Test
    void testUpdateUserFromDTO_NullDTO() {
        UserUpdateDTO updateDTO = null;
        UserDetail userDetail = new UserDetail();
        userDetail.setUsername("existingUser");

        UserUpdateMapper.INSTANCE.toEntity(updateDTO);

        assertEquals("existingUser", userDetail.getUsername());
    }

    @Test
    void testUpdateUserFromDTO() {
        UserUpdateDTO updateDTO = UserUpdateDTO.builder()
                .username("newUser")
                .build();

        UserUpdateMapper.INSTANCE.updateEntityFromDto(updateDTO, userDetail);

        assertEquals("newUser", userDetail.getUsername());
        assertEquals("password123", userDetail.getPassword());
    }
}
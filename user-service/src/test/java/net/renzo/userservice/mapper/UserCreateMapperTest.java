package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.dto.ProfileDTO;
import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.model.Address;
import net.renzo.userservice.model.Profile;
import net.renzo.userservice.model.UserDetail;
import net.renzo.userservice.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserCreateMapperTest {

    @Mock
    private ProfileMapper profileMapper;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private UserCreateMapperImpl userCreateMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldMapUserCreateDTOToUserDetail() {
        // Given
        lenient().when(profileMapper.toEntity(any(ProfileDTO.class)))
                .thenAnswer(invocation -> {
                    ProfileDTO dto = invocation.getArgument(0);
                    Profile profile = new Profile();
                    profile.setBio(dto.getBio());
                    profile.setProfilePictureUrl(dto.getProfilePictureUrl());
                    return profile;
                });

        lenient().when(addressMapper.toEntity(any(AddressDTO.class)))
                .thenAnswer(invocation -> {
                    AddressDTO dto = invocation.getArgument(0);
                    return Address.builder()
                            .street(dto.getStreet())
                            .city(dto.getCity())
                            .state(dto.getState())
                            .zipCode(dto.getZipCode())
                            .country(dto.getCountry())
                            .build();
                });


        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .username("testuser")
                .password("password123")
                .email("test@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(UserRole.ADMIN)
                .address(AddressDTO.builder()
                        .street("123 Main St")
                        .city("Test City")
                        .state("Test State")
                        .country("Test Country")
                        .zipCode("12345")
                        .build())
                .profile(ProfileDTO.builder()
                        .bio("samplebio")
                        .profilePictureUrl("sampleurl")
                        .build())
                .build();

        // When
        UserDetail userDetail = userCreateMapper.toEntity(userCreateDTO);

        // Then
        assertThat(userDetail).isNotNull();
        assertThat(userDetail.getUsername()).isEqualTo(userCreateDTO.getUsername());
        assertThat(userDetail.getPassword()).isEqualTo(userCreateDTO.getPassword());
        assertThat(userDetail.getEmail()).isEqualTo(userCreateDTO.getEmail());
        assertThat(userDetail.getFirstName()).isEqualTo(userCreateDTO.getFirstName());
        assertThat(userDetail.getLastName()).isEqualTo(userCreateDTO.getLastName());
        assertThat(userDetail.getRole()).isEqualTo(userCreateDTO.getRole());

        // Address assertions
        assertThat(userDetail.getAddresses().getStreet())
                .isEqualTo(userCreateDTO.getAddress().getStreet());
        assertThat(userDetail.getAddresses().getCity())
                .isEqualTo(userCreateDTO.getAddress().getCity());
        assertThat(userDetail.getAddresses().getState())
                .isEqualTo(userCreateDTO.getAddress().getState());
        assertThat(userDetail.getAddresses().getCountry())
                .isEqualTo(userCreateDTO.getAddress().getCountry());
        assertThat(userDetail.getAddresses().getZipCode())
                .isEqualTo(userCreateDTO.getAddress().getZipCode());

        // Profile assertions
        assertThat(userDetail.getProfile()).isNotNull();
        assertThat(userDetail.getProfile().getBio())
                .isEqualTo(userCreateDTO.getProfile().getBio());
        assertThat(userDetail.getProfile().getProfilePictureUrl())
                .isEqualTo(userCreateDTO.getProfile().getProfilePictureUrl());
    }

    @Test
    void shouldHandleNullProfileAndAddress() {
        // Given
        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .username("testuser")
                .password("password123")
                .email("test@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(UserRole.ADMIN)
                .build();

        // When
        UserDetail userDetail = userCreateMapper.toEntity(userCreateDTO);

        // Then
        assertThat(userDetail).isNotNull();
        assertThat(userDetail.getUsername()).isEqualTo(userCreateDTO.getUsername());
        assertThat(userDetail.getPassword()).isEqualTo(userCreateDTO.getPassword());
        assertThat(userDetail.getEmail()).isEqualTo(userCreateDTO.getEmail());
        assertThat(userDetail.getFirstName()).isEqualTo(userCreateDTO.getFirstName());
        assertThat(userDetail.getLastName()).isEqualTo(userCreateDTO.getLastName());
        assertThat(userDetail.getRole()).isEqualTo(userCreateDTO.getRole());
        assertThat(userDetail.getAddresses()).isNull();
        assertThat(userDetail.getProfile()).isNull();
    }
}
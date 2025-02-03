package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.ProfileDTO;
import net.renzo.userservice.model.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ProfileMapperTest {

    @InjectMocks
    private ProfileMapperImpl profileMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toDTO() {
        // Arrange
        Profile profile = Profile.builder()
                .bio("sample bio")
                .profilePictureUrl("sample url")
                .build();

        // Act
        ProfileDTO profileDTO = profileMapper.toDTO(profile);

        // Assert
        assertThat(profileDTO).isNotNull();
        assertThat(profileDTO.getBio()).isEqualTo(profile.getBio());
        assertThat(profileDTO.getProfilePictureUrl()).isEqualTo(profile.getProfilePictureUrl());
    }

    @Test
    void toEntity() {
        // Arrange
        ProfileDTO profileDTO = ProfileDTO.builder()
                .bio("sample bio")
                .profilePictureUrl("sample url")
                .build();

        // Act
        Profile profile = profileMapper.toEntity(profileDTO);

        // Assert
        assertThat(profile).isNotNull();
        assertThat(profile.getBio()).isEqualTo(profileDTO.getBio());
        assertThat(profile.getProfilePictureUrl()).isEqualTo(profileDTO.getProfilePictureUrl());
    }

    @Test
    void toDTO_Null() {
        // Act
        ProfileDTO profileDTO = profileMapper.toDTO(null);

        // Assert
        assertThat(profileDTO).isNull();
    }

    @Test
    void toEntity_Null() {
        // Act
        Profile profile = profileMapper.toEntity(null);

        // Assert
        assertThat(profile).isNull();
    }
}
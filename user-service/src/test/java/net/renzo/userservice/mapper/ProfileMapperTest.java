package net.renzo.userservice.mapper;

import net.renzo.userservice.dto.ProfileDTO;
import net.renzo.userservice.model.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;


class ProfileMapperTest {

    private ProfileMapper profileMapper;

    @Test
    void toDTO() {
        //Arrange
        Profile profile = Profile.builder()
                .bio("sample bio")
                .profilePictureUrl("sample url")
                .build();

        //Act
        ProfileDTO profileDTO = profileMapper.toDTO(profile);

        //Assert
        assertThat(profile.getBio()).isEqualTo(profileDTO.getBio());
        assertThat(profile.getProfilePictureUrl()).isEqualTo(profileDTO.getProfilePictureUrl());
    }

    @Test
    void toEntity() {
        //Arrange
        ProfileDTO profileDTO = ProfileDTO.builder()
                .bio("sample bio")
                .profilePictureUrl("sample url")
                .build();

        //Act
        Profile profile = profileMapper.toEntity(profileDTO);

        //Assert
        assertThat(profileDTO.getBio()).isEqualTo(profile.getBio());
        assertThat(profileDTO.getProfilePictureUrl()).isEqualTo(profile.getProfilePictureUrl());
    }

    @Test
    void toDTO_Null() {
        //Arrange
        Profile profile = Profile.builder()
                .bio(null)
                .profilePictureUrl(null)
                .build();

        //Act
        ProfileDTO profileDTO = profileMapper.toDTO(profile);

        //Assert
        assertThat(profile.getBio()).isNull();
        assertThat(profile.getProfilePictureUrl()).isNull();
    }
}
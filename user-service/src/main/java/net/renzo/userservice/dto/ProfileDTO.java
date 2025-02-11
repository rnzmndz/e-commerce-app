package net.renzo.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import net.renzo.userservice.util.ValidationPatterns;

@Data
@Builder
public class ProfileDTO {

    @Schema(description = "The bio of the user. This field must not contain invalid characters.", example = "Software developer with 10 years of experience.")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?\\s]*$", message = "Field contains invalid characters")
    private String bio;

    @Schema(description = "The URL of the user's profile picture. This field must not contain invalid characters.", example = "http://example.com/profile.jpg")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String profilePictureUrl;
}
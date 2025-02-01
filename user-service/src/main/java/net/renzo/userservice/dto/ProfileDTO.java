package net.renzo.userservice.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import net.renzo.userservice.util.ValidationPatterns;

@Data
@Builder
public class ProfileDTO {

    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String bio;
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String profilePictureUrl;
}

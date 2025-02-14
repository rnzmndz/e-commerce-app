package net.renzo.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import net.renzo.userservice.util.ValidationPatterns;

public class UserBasicInfoDTO {

    @Schema(description = "User ID", example = "1")
    private String id;

    @Schema(description = "Username", example = "johndoe")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Username must not contain special characters")
    private String username;

    @Schema(description = "Email", example = "sample@email.com")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Invalid email format")
    private String email;
}

package net.renzo.userservice.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import net.renzo.userservice.util.ValidationPatterns;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@Schema(description = "Data Transfer Object for user credentials")
public class UserCredentialsDTO {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    @Schema(description = "Username of the user", example = "john_doe")
    private String username;

    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    @Schema(description = "Password of the user", example = "P@ssw0rd")
    private String password;
}

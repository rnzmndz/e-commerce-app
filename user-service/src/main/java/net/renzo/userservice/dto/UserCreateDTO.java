package net.renzo.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.renzo.userservice.model.UserRole;
import net.renzo.userservice.util.ValidationPatterns;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object for creating a new user")
public class UserCreateDTO {

    @NotNull(message = "username cannot be null")
    @Size(min = 3, max = 50)
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    @Schema(description = "Username of the user", example = "john_doe")
    private String username;

    @NotNull(message = "password cannot be null")
    @Size(min = 6)
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    @Schema(description = "Password of the user", example = "P@ssw0rd")
    private String password;

    @Email
    @NotNull
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @NotNull(message = "First name cannot be null")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    @Schema(description = "Role of the user")
    private UserRole role;

    @Schema(description = "Address of the user")
    private AddressDTO address;

    @Schema(description = "Profile of the user")
    private ProfileDTO profile;
}


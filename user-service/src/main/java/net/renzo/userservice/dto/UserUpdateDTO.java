package net.renzo.userservice.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.renzo.userservice.util.ValidationPatterns;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for updating user details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDTO {

    @Schema(description = "Username of the user", example = "john_doe")
    @Nullable
    @Size(min = 3, max = 50)
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String username;

    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    @Nullable
    @Email
    private String email;

    @Schema(description = "First name of the user", example = "John")
    @Nullable
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe")
    @Nullable
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String lastName;

    @Schema(description = "Phone number of the user", example = "+1234567890")
    @Nullable
    private String phoneNumber;

    @Schema(description = "Address of the user")
    @Nullable
    private AddressDTO address;
}

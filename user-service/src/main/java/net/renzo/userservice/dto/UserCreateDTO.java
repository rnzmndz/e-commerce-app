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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    @NotNull(message = "username cannot be null")
    @Size(min = 3, max = 50)
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String username;

    @NotNull(message = "password cannot be null")
    @Size(min = 6)
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String password;

    @Email
    @NotNull
    private String email;

    @NotNull(message = "First name cannot be null")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String lastName;

    private UserRole role;
    private AddressDTO address;
    private ProfileDTO profile;


}

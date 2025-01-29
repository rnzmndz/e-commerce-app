package net.renzo.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import net.renzo.userservice.model.UserRole;

@Data
@Builder
public class UserCreateDTO {

    @NotNull(message = "username cannot be null")
    @Size(min = 3, max = 50)
    private String username;

    @NotNull(message = "password cannot be null")
    @Size(min = 6)
    private String password;

    @Email
    @NotNull
    private String email;

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    private UserRole role;
    private AddressDTO address;
    private ProfileDTO profile;
}

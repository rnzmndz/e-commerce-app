package net.renzo.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.renzo.userservice.model.UserRole;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object for User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Schema(description = "The unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "The username of the user", example = "john_doe")
    private String username;

    @Schema(description = "The email address of the user", example = "john.doe@example.com")
    private String email;

    @Schema(description = "The role of the user")
    private UserRole role;

    @Schema(description = "The first name of the user", example = "John")
    private String firstName;

    @Schema(description = "The last name of the user", example = "Doe")
    private String lastName;

    @Schema(description = "The address of the user")
    private AddressDTO addresses;

    @Schema(description = "The profile of the user")
    private ProfileDTO profile;

    @Schema(description = "The authorities granted to the user",type = "array", example = "[\"ROLE_USER\", \"ROLE_ADMIN\"]")
    private Set<String> authorities;

    @Schema(description = "The date and time when the user was created", example = "2023-01-01T12:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "The date and time when the user was last updated", example = "2023-01-02T12:00:00")
    private LocalDateTime updatedAt;
}

package net.renzo.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for listing user details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserListDTO {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "Username of the user", example = "johndoe")
    private String username;

    @Schema(description = "Email address of the user", example = "johndoe@example.com")
    private String email;

    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;
}

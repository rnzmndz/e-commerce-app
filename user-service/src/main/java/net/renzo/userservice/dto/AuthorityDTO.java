package net.renzo.userservice.dto;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import net.renzo.userservice.util.ValidationPatterns;

@Data
@Schema(description = "Data Transfer Object for Authority, representing the authority details.")
public class AuthorityDTO {

    @Schema(description = "The unique identifier of the authority.", example = "1")
    private Long id;

    @Schema(description = "The name of the authority. This field must not contain invalid characters.", example = "ROLE_USER")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Invalid name format or contains disallowed characters.")
    private String name;
}

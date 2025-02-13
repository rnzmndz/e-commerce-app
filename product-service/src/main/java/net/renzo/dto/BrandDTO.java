package net.renzo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import net.renzo.util.ValidationPatterns;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Data Transfer Object for Brand")
public class BrandDTO {
    @Schema(description = "Unique identifier of the brand", example = "1")
    private Long id;

    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Invalid characters in name")
    @Schema(description = "Name of the brand", example = "BrandName")
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?\\s]*$", message = "Invalid characters in description")
    @Schema(description = "Description of the brand", example = "This is a brand description.")
    private String description;

    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Invalid characters in logo")
    @Schema(description = "Logo URL of the brand", example = "http://example.com/logo.png")
    private String logo;
}
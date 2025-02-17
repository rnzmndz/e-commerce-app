package net.renzo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import net.renzo.util.ValidationPatterns;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDTO {

    @Schema(description = "The unique identifier of the product attribute.", example = "1")
    private Long id;

    @Schema(description = "The key of the product attribute.", example = "color")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String key;

    @Schema(description = "The value of the product attribute.", example = "red")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String value;
}
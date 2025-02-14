package net.renzo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import net.renzo.util.ValidationPatterns;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object for updating a product")
public class ProductUpdateDTO {

    @Schema(description = "The unique identifier of the product", example = "1")
    private Long id;

    @Schema(description = "The name of the product", example = "Laptop")
    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Name cannot contain special characters")
    private String name;

    @Schema(description = "The SKU of the product", example = "SKU12345")
    @NotNull(message = "SKU cannot be null")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "SKU cannot contain special characters")
    private String sku;

    @Schema(description = "The description of the product", example = "A high-end gaming laptop")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN_WITH_SPACE, message = "Description cannot contain special characters")
    private String description;

}
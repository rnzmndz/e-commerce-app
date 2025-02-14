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

    @Schema(description = "The description of the product", example = "A high-end gaming laptop")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN_WITH_SPACE, message = "Description cannot contain special characters")
    private String description;

    @Schema(description = "The price of the product", example = "999.99")
    @NotNull(message = "Price cannot be null")
    private Double price;

    @Schema(description = "The name of the category", example = "Electronics")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Category name cannot contain special characters")
    private String categoryName;

    @Schema(description = "The name of the brand", example = "BrandX")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Brand name cannot contain special characters")
    private String brandName;
}
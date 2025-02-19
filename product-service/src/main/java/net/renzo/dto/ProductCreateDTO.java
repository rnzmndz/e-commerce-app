package net.renzo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import net.renzo.util.ValidationPatterns;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {

    @NotNull
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Invalid characters in name")
    @Schema(description = "The name of the product.", example = "Smartphone")
    private String name;

    @NotNull
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN_WITH_SPACE, message = "Invalid characters in description")
    @Schema(description = "The description of the product.", example = "A high-end smartphone with 128GB storage.")
    private String description;

    @NotNull
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Invalid characters in SKU")
    @Schema(description = "The SKU of the product.", example = "SKU12345")
    private String sku;

    @NotNull
    @Schema(description = "The category of the product.", example = "Electronics")
    private Set<CategoryDTO> categories;

    @NotNull
    @Schema(description = "The brand of the product.", example = "Apple")
    private BrandDTO brand;

    @Schema(description = "The default image of the product.")
    private Set<ImageDTO> images;

    @Schema(description = "The default variant of the product.")
    private Set<VariantDTO> variants;

    @Schema(description = "The default attribute of the product.")
    private Set<AttributeDTO> attributes;

    @Schema(description = "The default review of the product.")
    private Set<ReviewDTO> reviews;
}
package net.renzo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import net.renzo.util.ValidationPatterns;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariantDTO {
    @Schema(description = "Unique identifier of the product variant", example = "1")
    @NotNull(message = "Id should not be null")
    private Long id;

    @Schema(description = "Name of the product variant", example = "Variant A")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Name should not contain special characters")
    private String name;

    @Schema(description = "Price details of the product variant")
    @NotNull(message = "Price should not be null")
    private PriceDTO price;

    @Schema(description = "Stock available for the product variant", example = "100")
    @NotNull(message = "Stock should not be null")
    private Integer stock;

    @Schema(description = "Attributes of the product variant")
    @NotNull(message = "Attributes should not be null")
    private Set<AttributeDTO> attributes;
}

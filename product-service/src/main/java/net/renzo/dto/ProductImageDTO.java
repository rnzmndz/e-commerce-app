package net.renzo.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import net.renzo.util.ValidationPatterns;

@Getter
@Setter
@Schema(description = "Data Transfer Object for Product Image")
public class ProductImageDTO {

    @Schema(description = "The unique identifier of the product image.", example = "1")
    private Long id;

    @Schema(description = "The URL of the product image.", example = "http://example.com/image.jpg")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String url;

    @Schema(description = "The unique identifier of the product.", example = "100")
    private Long productId;
}
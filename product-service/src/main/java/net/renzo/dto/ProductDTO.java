package net.renzo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @Schema(description = "The unique identifier of the product.", example = "1")
    private Long id;

    @NotNull
    @Schema(description = "The name of the product.", example = "Smartphone")
    private String name;

    @NotNull
    @Schema(description = "The description of the product.", example = "A high-end smartphone with 128GB storage.")
    private String description;

    @NotNull
    @Schema(description = "The SKU of the product.", example = "SKU12345")
    private String sku;

    @NotNull
    @Schema(description = "The category of the product.", example = "Electronics")
    private String categoryName;

    @NotNull
    @Schema(description = "The brand of the product.", example = "Apple")
    private String brandName;

    @Schema(description = "The default image of the product.")
    private String defaultImage;
}
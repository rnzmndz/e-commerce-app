package net.renzo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDTO {

    @Schema(description = "The unique identifier of the product.", example = "1")
    private Long id;

    @Schema(description = "The name of the product.", example = "Smartphone")
    private String name;

    @Schema(description = "The description of the product.", example = "A high-end smartphone with 128GB storage.")
    private String description;

    @Schema(description = "The SKU of the product.", example = "SKU12345")
    private String sku;

    @Schema(description = "The default image of the product.")
    private ImageDTO defaultImage;

    @Schema(description = "The category name of the product.", example = "Electronics")
    private String categoryName;

    @Schema(description = "The brand name of the product.", example = "BrandX")
    private String brandName;
}

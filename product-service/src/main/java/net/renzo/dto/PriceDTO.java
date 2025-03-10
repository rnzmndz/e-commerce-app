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
@Schema(description = "Data Transfer Object for Price")
public class PriceDTO {

    @Schema(description = "Unique identifier of the price", example = "1")
    private Long id;

    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Invalid characters in amount")
    @Schema(description = "Amount of the price", example = "99.99")
    private Double amount;

    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Invalid characters in currency")
    @Schema(description = "Currency of the price", example = "USD")
    private String currency;
}
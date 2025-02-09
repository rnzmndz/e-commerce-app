package net.renzo.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.renzo.userservice.util.ValidationPatterns;

/**
 * Data Transfer Object for Address.
 * <p>
 * This class represents an address with fields for street, city, state, zip code, and country.
 * It includes validation patterns to ensure that fields do not contain invalid characters.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {


    /**
     * The street address.
     * This field must not contain invalid characters.
     */
    @Schema(description = "The street address. This field must not contain invalid characters.", example = "123 Main St")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?\\s]*$", message = "Invalid street format or contains disallowed characters.")
    private String street;

    /**
     * The city of the address.
     * This field must not contain invalid characters.
     */
    @Schema(description = "The city of the address. This field must not contain invalid characters.", example = "New York")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?\\s]*$", message = "Invalid city format or contains disallowed characters.")
    private String city;

    /**
     * The state of the address.
     * This field must not contain invalid characters.
     */
    @Schema(description = "The state of the address. This field must not contain invalid characters.", example = "NY")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String state;

    /**
     * The zip code of the address.
     * This field must not contain invalid characters.
     */
    @Schema(description = "The zip code of the address. This field must not contain invalid characters.", example = "10001")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String zipCode;

    /**
     * The country of the address.
     * This field must not contain invalid characters.
     */
    @Schema(description = "The country of the address. This field must not contain invalid characters.", example = "USA")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String country;
}
package net.renzo.userservice.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
@AllArgsConstructor
public class AddressDTO {


    /**
     * The street address.
     * This field must not contain invalid characters.
     */
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String street;

    /**
     * The city of the address.
     * This field must not contain invalid characters.
     */
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String city;

    /**
     * The state of the address.
     * This field must not contain invalid characters.
     */
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String state;

    /**
     * The zip code of the address.
     * This field must not contain invalid characters.
     */
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String zipCode;

    /**
     * The country of the address.
     * This field must not contain invalid characters.
     */
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Field contains invalid characters")
    private String country;
}

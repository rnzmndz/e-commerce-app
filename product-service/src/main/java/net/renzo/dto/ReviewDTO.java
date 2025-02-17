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
public class ReviewDTO {

    @Schema(description = "The unique identifier of the product review.", example = "1")
    private Long id;

    @Schema(description = "The rating given in the product review.", example = "4")
    @NotNull(message = "Rating cannot be null")
    private Integer rating;

    @Schema(description = "The comment provided in the product review.", example = "Great product!")
    @Pattern(regexp = ValidationPatterns.SPECIAL_CHARACTERS_PATTERN, message = "Comment cannot contain special characters")
    private String comment;

    @Schema(description = "The unique identifier of the user being reviewed.", example = "10")
    @NotNull(message = "User ID cannot be null")
    private Long userId;
}
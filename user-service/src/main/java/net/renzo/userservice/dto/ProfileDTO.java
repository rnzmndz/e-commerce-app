package net.renzo.userservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileDTO {

    private String bio;
    private String profilePictureUrl;
}

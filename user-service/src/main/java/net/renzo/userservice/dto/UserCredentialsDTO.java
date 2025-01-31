package net.renzo.userservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCredentialsDTO {

    private Long id;
    private String username;
    private String password;
}

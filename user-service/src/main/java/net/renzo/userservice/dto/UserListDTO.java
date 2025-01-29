package net.renzo.userservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserListDTO {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}

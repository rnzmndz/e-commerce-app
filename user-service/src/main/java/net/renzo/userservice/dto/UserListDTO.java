package net.renzo.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserListDTO {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}

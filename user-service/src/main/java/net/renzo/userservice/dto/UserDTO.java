package net.renzo.userservice.dto;

import lombok.Builder;
import lombok.Data;
import net.renzo.userservice.model.UserRole;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private UserRole role;
    private String firstName;
    private String lastName;
    private List<AddressDTO> addresses;
    private ProfileDTO profile;
    private Set<String> authorities;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

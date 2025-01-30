package net.renzo.userservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}

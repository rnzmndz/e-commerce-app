package net.renzo.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.service.AddressService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//TODO Create Swagger documentation for the AddressController class
@RestController
@RequestMapping("api/v1/address")
@Tag(name = "Address", description = "Address API")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Get address by user ID", description = "Retrieve the address details for a given user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved address"),
            @ApiResponse(responseCode = "400", description = "Invalid user ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content)
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> getAddressByUserId(@Parameter(description = "ID of the user to retrieve address", required = true) @PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        AddressDTO addressDTO = addressService.getAddressByUserId(id);
        return ResponseEntity.ok(addressDTO);
    }

    @Operation(summary = "Update address", description = "Update the address details for a given user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated address"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content)
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> updateAddress(@Parameter(description = "ID of the user to update address", required = true) @PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO) {
        AddressDTO updatedAddress = addressService.updateAddress(id, addressDTO);
        return ResponseEntity.ok(updatedAddress);
    }
}

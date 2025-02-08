package net.renzo.userservice.controller;

import jakarta.validation.Valid;
import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {
private final AddressService addressService;

        public AddressController(AddressService addressService) {
            this.addressService = addressService;
        }

        @GetMapping("/{id}")
        public ResponseEntity<AddressDTO> getAddressByUserId(@PathVariable Long id) {
            AddressDTO addressDTO = addressService.getAddressByUserId(id);
            return ResponseEntity.ok(addressDTO);
        }

        @PutMapping("/{id}")
        public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO) {
            AddressDTO updatedAddress = addressService.updateAddress(id, addressDTO);
            return ResponseEntity.ok(updatedAddress);
        }
}

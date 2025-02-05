package net.renzo.userservice.controller;

import jakarta.validation.Valid;
import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {
  // FIXME: Add the missing methods to the AddressController class
private final AddressService addressService;

        public AddressController(AddressService addressService) {
            this.addressService = addressService;
        }

        @PostMapping
        public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressCreateDTO) {
            AddressDTO createdAddress = addressService.createAddress(addressCreateDTO);
            return ResponseEntity.ok(createdAddress);
        }

        @GetMapping("/{id}")
        public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
            AddressDTO addressDTO = addressService.getAddressById(id);
            return ResponseEntity.ok(addressDTO);
        }

        @GetMapping
        public ResponseEntity<List<AddressDTO>> getAllAddresses() {
            List<AddressDTO> addresses = addressService.getAllAddresses();
            return ResponseEntity.ok(addresses);
        }

        @PutMapping("/{id}")
        public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressUpdateDTO addressUpdateDTO) {
            AddressDTO updatedAddress = addressService.updateAddress(id, addressUpdateDTO);
            return ResponseEntity.ok(updatedAddress);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
            addressService.deleteAddress(id);
            return ResponseEntity.noContent().build();
        }
}

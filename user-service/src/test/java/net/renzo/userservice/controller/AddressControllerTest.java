package net.renzo.userservice.controller;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAddressByUserId() {
        AddressDTO addressDTO = new AddressDTO();
        when(addressService.getAddressByUserId(anyLong())).thenReturn(addressDTO);

        ResponseEntity<AddressDTO> response = addressController.getAddressByUserId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(addressDTO, response.getBody());
    }

    @Test
    void testUpdateAddress() {
        AddressDTO addressDTO = new AddressDTO();
        when(addressService.updateAddress(anyLong(), any(AddressDTO.class))).thenReturn(addressDTO);

        ResponseEntity<AddressDTO> response = addressController.updateAddress(1L, addressDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(addressDTO, response.getBody());
    }
}
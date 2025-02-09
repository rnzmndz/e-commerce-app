package net.renzo.userservice.service;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.exception.AddressNotFoundException;
import net.renzo.userservice.exception.UserNotFoundException;
import net.renzo.userservice.mapper.AddressMapper;
import net.renzo.userservice.model.Address;
import net.renzo.userservice.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressServiceImpl addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateAddress() {
        // Arrange
        Long addressId = 1L;
        AddressDTO addressDTO = new AddressDTO();
        Address existingAddress = new Address();
        Address updatedAddress = new Address();
        AddressDTO updatedAddressDTO = new AddressDTO();

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(existingAddress));
        doNothing().when(addressMapper).updateEntityFromDto(addressDTO, existingAddress);
        when(addressRepository.save(existingAddress)).thenReturn(updatedAddress);
        when(addressMapper.toDTO(updatedAddress)).thenReturn(updatedAddressDTO);

        // Act
        AddressDTO result = addressService.updateAddress(addressId, addressDTO);

        // Assert
        assertEquals(updatedAddressDTO, result);
        verify(addressRepository).findById(addressId);
        verify(addressMapper).updateEntityFromDto(addressDTO, existingAddress);
        verify(addressRepository).save(existingAddress);
        verify(addressMapper).toDTO(updatedAddress);
    }

    @Test
    void testUpdateAddress_AddressNotFound() {
        // Arrange
        Long addressId = 1L;
        AddressDTO addressDTO = new AddressDTO();

        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AddressNotFoundException.class, () -> addressService.updateAddress(addressId, addressDTO));
        verify(addressRepository).findById(addressId);
        verify(addressMapper, never()).updateEntityFromDto(any(), any());
        verify(addressRepository, never()).save(any());
        verify(addressMapper, never()).toDTO(any());
    }

    @Test
    void testGetAddressByUserId() {
        // Arrange
        Long userId = 1L;
        Address address = new Address();
        AddressDTO addressDTO = new AddressDTO();

        when(addressRepository.findByUserId(userId)).thenReturn(Optional.of(address));
        when(addressMapper.toDTO(address)).thenReturn(addressDTO);

        // Act
        AddressDTO result = addressService.getAddressByUserId(userId);

        // Assert
        assertEquals(addressDTO, result);
        verify(addressRepository).findByUserId(userId);
        verify(addressMapper).toDTO(address);
    }

    @Test
    void testGetAddressByUserId_AddressNotFound() {
        // Arrange
        Long userId = 1L;

        when(addressRepository.findByUserId(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AddressNotFoundException.class, () -> addressService.getAddressByUserId(userId));
        verify(addressRepository).findByUserId(userId);
        verify(addressMapper, never()).toDTO(any());
    }
}
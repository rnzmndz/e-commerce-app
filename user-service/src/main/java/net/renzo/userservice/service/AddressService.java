package net.renzo.userservice.service;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.model.Address;

import java.util.List;

public interface AddressService {


    /**
     * Updates an existing address.
     *
     * @param addressId the ID of the address to update
     * @param addressDTO the address data transfer object containing updated address details
     * @return the updated Address
     */
    AddressDTO updateAddress(Long addressId, AddressDTO addressDTO);

    /**
     * Retrieves an address by user ID.
     *
     * @param userId the ID of the user
     * @return the Address associated with the user ID
     */
    AddressDTO getAddressByUserId(Long userId);

}

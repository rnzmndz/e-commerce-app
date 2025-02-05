package net.renzo.userservice.service;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.model.Address;

public interface AddressService {

    /**
         * Creates a new address for a user.
         *
         * @param userId the ID of the user
         * @param addressDTO the address data transfer object containing address details
         * @return the created Address
         */
        Address createAddress(Long userId, AddressDTO addressDTO);

        /**
         * Updates an existing address.
         *
         * @param addressId the ID of the address to update
         * @param addressDTO the address data transfer object containing updated address details
         * @return the updated Address
         */
        Address updateAddress(Long addressId, AddressDTO addressDTO);

        /**
         * Retrieves an address by user ID.
         *
         * @param userId the ID of the user
         * @return the Address associated with the user ID
         */
        Address getAddressByUserId(Long userId);

        /**
         * Deletes an address by ID.
         *
         * @param addressId the ID of the address to delete
         */
        void deleteAddress(Long addressId);
}

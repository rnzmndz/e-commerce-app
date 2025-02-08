package net.renzo.userservice.service;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.exception.AddressNotFoundException;
import net.renzo.userservice.exception.UserNotFoundException;
import net.renzo.userservice.mapper.AddressMapper;
import net.renzo.userservice.model.Address;
import net.renzo.userservice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

   /**
     * Updates an existing address with new details.
     *
     * @param addressId the ID of the address to be updated
     * @param addressDTO the data transfer object containing the new address details
     * @return the updated Address entity
     * @throws IllegalArgumentException if the addressId or addressDTO is null
     * @throws AddressNotFoundException if the address is not found
     */
    @Override
    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
        // Validate the input parameters
        if (addressId == null || addressDTO == null) {
            throw new IllegalArgumentException("Address ID and AddressDTO must not be null");
        }

        // Retrieve the existing address from the AddressRepository
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        // Update the existing address with the new details from AddressDTO
        addressMapper.updateEntityFromDto(addressDTO, existingAddress);

        // Save the updated address back to the repository
        Address savedAddress = addressRepository.save(existingAddress);
        return addressMapper.toDTO(savedAddress);
    }

    /**
     * Retrieves the address associated with a user by user ID.
     *
     * @param userId the ID of the user whose address is being retrieved
     * @return the retrieved Address entity
     * @throws IllegalArgumentException if the userId is null
     * @throws UserNotFoundException if the user is not found
     * @throws AddressNotFoundException if the address is not found for the user
     */
    @Override
    public AddressDTO getAddressByUserId(Long userId) {
        // Validate the input parameter
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        // Retrieve the address associated with the user
        Address address = addressRepository.findByUserId(userId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found for user"));

        // Return the retrieved address
        return addressMapper.toDTO(address);
    }

}

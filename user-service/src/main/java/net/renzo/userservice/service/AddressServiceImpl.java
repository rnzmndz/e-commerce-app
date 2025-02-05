package net.renzo.userservice.service;

import net.renzo.userservice.dto.AddressDTO;
import net.renzo.userservice.exception.AddressNotFoundException;
import net.renzo.userservice.exception.UserNotFoundException;
import net.renzo.userservice.mapper.AddressMapper;
import net.renzo.userservice.model.Address;
import net.renzo.userservice.model.UserDetail;
import net.renzo.userservice.repository.AddressRepository;
import net.renzo.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    public AddressServiceImpl(UserRepository userRepository, AddressRepository addressRepository, AddressMapper addressMapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

   /**
     * Creates a new address for a user.
     *
     * @param userId the ID of the user for whom the address is being created
     * @param addressDTO the data transfer object containing the address details
     * @return the created Address entity
     * @throws IllegalArgumentException if the userId or addressDTO is null
     * @throws UserNotFoundException if the user is not found
     */
    @Override
    public Address createAddress(Long userId, AddressDTO addressDTO) {
        // Validate the input parameters
        if (userId == null || addressDTO == null) {
            throw new IllegalArgumentException("User ID and AddressDTO must not be null");
        }

        // Retrieve the user from the UserRepository
        UserDetail user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Convert AddressDTO to Address entity
        Address address = addressMapper.toEntity(addressDTO);

        // Return the saved Address entity
        return addressRepository.save(address);
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
    public Address updateAddress(Long addressId, AddressDTO addressDTO) {
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
        return addressRepository.save(existingAddress);
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
    public Address getAddressByUserId(Long userId) {
        // Validate the input parameter
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        // Retrieve the user from the UserRepository
        UserDetail user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Retrieve the address associated with the user
        Address address = addressRepository.findByUserId(userId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found for user"));

        // Return the retrieved address
        return address;
    }

    /**
     * Deletes an existing address by address ID.
     *
     * @param addressId the ID of the address to be deleted
     * @throws IllegalArgumentException if the addressId is null
     * @throws AddressNotFoundException if the address is not found
     */
    @Override
    public void deleteAddress(Long addressId) {
        // Validate the input parameter
        if (addressId == null) {
            throw new IllegalArgumentException("Address ID must not be null");
        }

        // Retrieve the existing address from the AddressRepository
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        // Delete the address from the repository
        addressRepository.delete(existingAddress);
    }
}

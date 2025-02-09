package net.renzo.userservice.service;

import jakarta.transaction.Transactional;
import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.dto.UserDTO;
import net.renzo.userservice.dto.UserListDTO;
import net.renzo.userservice.dto.UserUpdateDTO;
import net.renzo.userservice.exception.AuthorityNotFoundException;
import net.renzo.userservice.exception.InvalidPasswordException;
import net.renzo.userservice.exception.UserNotFoundException;
import net.renzo.userservice.mapper.*;
import net.renzo.userservice.model.Authority;
import net.renzo.userservice.model.UserDetail;
import net.renzo.userservice.model.UserRole;
import net.renzo.userservice.repository.AuthorityRepository;
import net.renzo.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final UserMapper userMapper;
    private final UserCreateMapper userCreateMapper;
    private final UserListMapper userListMapper;
    private final UserUpdateMapper userUpdateMapper;

    private final AddressMapper addressMapper;
    private final ProfileMapper profileMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, UserCreateMapper userCreateMapper,
                           UserMapper userMapper, UserListMapper userListMapper, UserUpdateMapper userUpdateMapper,
                           AddressMapper addressMapper, ProfileMapper profileMapper) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.userCreateMapper = userCreateMapper;
        this.userMapper = userMapper;
        this.userListMapper = userListMapper;
        this.userUpdateMapper = userUpdateMapper;
        this.addressMapper = addressMapper;
        this.profileMapper = profileMapper;
    }

    @Override
    @Transactional
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        // Check if a user with the same username or email already exists
        if (userRepository.existsByUsername(userCreateDTO.getUsername()) || userRepository.existsByEmail(userCreateDTO.getEmail())) {
            throw new UserNotFoundException("User with the same username or email already exists");
        }

        // Fetch the default authority (ROLE_USER) from the database
        Authority defaultAuthority = authorityRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new AuthorityNotFoundException("Default authority not found in the database."));

        // Create a new UserDetail entity from the UserCreateDTO using a mapper
        UserDetail userDetail = userCreateMapper.toEntity(userCreateDTO);

        userDetail.addAuthorities(defaultAuthority);

        // Check if the address is provided in the DTO
        if (userCreateDTO.getAddress() != null) {
            // Convert the address DTO to an entity and set it to the user
            userDetail.setAddresses(addressMapper.toEntity(userCreateDTO.getAddress()));
        }

        // Check if the profile is provided in the DTO
        if (userCreateDTO.getProfile() != null) {
            // Convert the profile DTO to an entity and set it to the user
            userDetail.setProfile(profileMapper.toEntity(userCreateDTO.getProfile()));
        }

        // Set the creation timestamp to the current time
        userDetail.setCreatedAt(LocalDateTime.now());

        // Set the update timestamp to the current time
        userDetail.setUpdatedAt(LocalDateTime.now());

        // Save the new user to the repository
        userDetail = userRepository.save(userDetail);

        // Convert the saved UserDetail entity to a UserDTO using the userMapper and return it
        return userMapper.toDTO(userDetail);
    }

    @Override
    public Optional<UserDTO> getById(Long id) {
        // Find the user by id, throw an exception if not found
        UserDetail userDetail = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // Convert the UserDetail entity to a UserDTO and return it
        return Optional.of(userMapper.toDTO(userDetail));
    }

    @Override
    public Page<UserListDTO> getAll(Pageable pageable) {
        // Fetch all UserDetail entities with pagination
        Page<UserDetail> userDetailsPage = userRepository.findAll(pageable);

        // Convert the Page<UserDetail> to Page<UserDTO> using the userMapper
        return userDetailsPage.map(userListMapper::toDTO);
    }

    @Override
    @Transactional
    public UserDTO updateById(Long id, UserUpdateDTO userUpdateDTO) {
        // Find the user by id, throw an exception if not found
        UserDetail userDetail = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // Update the user details with the new data from userUpdateDTO
        userUpdateMapper.updateEntityFromDto(userUpdateDTO, userDetail);

        // Save the updated user to the repository
        userDetail = userRepository.save(userDetail);

        // Convert the updated UserDetail entity to a UserDTO using the userMapper and return it
        return userMapper.toDTO(userDetail);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // Find the user by id, throw an exception if not found
        UserDetail userDetail = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // Delete the user from the repository
        userRepository.delete(userDetail);
    }

    @Override
    public Page<UserListDTO> getUserByRole(UserRole userRole, Pageable pageable) {
        // Fetch users by role with pagination
        Page<UserDetail> userDetailsPage = userRepository.findByRole(userRole, pageable);

        // Convert the Page<UserDetail> to Page<UserDTO> using the userMapper
        return userDetailsPage.map(userListMapper::toDTO);
    }
    /**
     * Checks if a user with the given username exists in the repository.
     *
     * @param username the username to check for existence
     * @return true if a user with the given username exists, false otherwise
     */
    @Override
    public boolean existsByUsername(String username) {
        // Call the repository method to check if the username exists
        return userRepository.existsByUsername(username);
    }

    /**
     * Checks if a user with the given email exists in the repository.
     *
     * @param email the email to check for existence
     * @return true if a user with the given email exists, false otherwise
     */
    @Override
    public boolean existsByEmail(String email) {
        // Call the repository method to check if the email exists
        return userRepository.existsByEmail(email);
    }
    @Override
    @Transactional
    public void changePassword(Long id, String newPassword) {
        // Find the user by id, throw an exception if not found
        UserDetail userDetail = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // Validate the new password to prevent SQL injection
        if (newPassword.matches(".*([';\\-\\-]).*")) {
            throw new InvalidPasswordException("Invalid password: potential SQL injection detected");
        }

        // Validate the new password if it the same as the old password
        if (newPassword.equals(userDetail.getPassword())) {
            throw new InvalidPasswordException("Invalid password: new password cannot be the same as the old password");
        }

        // Update the user's password
        userDetail.setPassword(newPassword);

        // Save the updated user to the repository
        userRepository.save(userDetail);
    }
}

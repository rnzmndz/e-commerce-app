package net.renzo.userservice.service;

import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.dto.UserDTO;
import net.renzo.userservice.dto.UserListDTO;
import net.renzo.userservice.dto.UserUpdateDTO;
import net.renzo.userservice.mapper.UserCreateMapper;
import net.renzo.userservice.mapper.UserListMapper;
import net.renzo.userservice.mapper.UserMapper;
import net.renzo.userservice.mapper.UserUpdateMapper;
import net.renzo.userservice.model.Authority;
import net.renzo.userservice.model.UserDetail;
import net.renzo.userservice.model.UserRole;
import net.renzo.userservice.repository.AuthorityRepository;
import net.renzo.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final UserMapper userMapper;
    private final UserCreateMapper userCreateMapper;
    private final UserListMapper userListMapper;
    private final UserUpdateMapper userUpdateMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, UserCreateMapper userCreateMapper,
                           UserMapper userMapper, UserListMapper userListMapper, UserUpdateMapper userUpdateMapper) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.userCreateMapper = userCreateMapper;
        this.userMapper = userMapper;
        this.userListMapper = userListMapper;
        this.userUpdateMapper = userUpdateMapper;
    }

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        // Check if a user with the same username or email already exists
        if (userRepository.existsByUsernameOrEmail(userCreateDTO.getUsername(), userCreateDTO.getEmail())) {
            throw new IllegalArgumentException("User with the same username or email already exists");
        }

        // Fetch the default authority (ROLE_USER) from the database
        Authority defaultAuthority = authorityRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default authority not found in the database."));

        // Create a new UserDetail entity from the UserCreateDTO using a mapper
        UserDetail userDetail = userCreateMapper.toEntity(userCreateDTO);
        userDetail.addAuthorities(defaultAuthority);

        // Save the new user to the repository
        userDetail = userRepository.save(userDetail);

        // Convert the saved UserDetail entity to a UserDTO using the userMapper and return it
        return userMapper.toDTO(userDetail);
    }

    @Override
    public Optional<UserDTO> getById(Long id) {
        // Find the user by id, throw an exception if not found
        UserDetail userDetail = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

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
    public UserDTO updateById(Long id, UserUpdateDTO userUpdateDTO) {
        // Find the user by id, throw an exception if not found
        UserDetail userDetail = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        // Update the user details with the new data from userUpdateDTO
        userUpdateMapper.updateEntityFromDto(userUpdateDTO, userDetail);

        // Save the updated user to the repository
        userDetail = userRepository.save(userDetail);

        // Convert the updated UserDetail entity to a UserDTO using the userMapper and return it
        return userMapper.toDTO(userDetail);
    }

    @Override
    public void deleteById(Long id) {
        // Find the user by id, throw an exception if not found
        UserDetail userDetail = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        // Delete the user from the repository
        userRepository.delete(userDetail);
    }

    @Override
    public UserDTO addAuthoritiesToUser(Long id, Set<Authority> authorities) {
        // Find the user by id, throw an exception if not found
        UserDetail userDetail = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        // Add the new authorities to the user
        for (Authority authority : authorities) {
            userDetail.addAuthorities(authority);
        }

        // Save the updated user to the repository
        userDetail = userRepository.save(userDetail);

        // Convert the updated UserDetail entity to a UserDTO using the userMapper and return it
        return userMapper.toDTO(userDetail);
    }

    @Override
    public UserDTO removeAuthorityFromUser(Long id, Authority authority) {
        // Find the user by id, throw an exception if not found
        UserDetail userDetail = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        // Remove the authority from the user
        userDetail.removeAuthorities(authority);

        // Save the updated user to the repository
        userDetail = userRepository.save(userDetail);

        // Convert the updated UserDetail entity to a UserDTO using the userMapper and return it
        return userMapper.toDTO(userDetail);
    }

    @Override
    public Page<UserListDTO> getUserByRole(UserRole userRole, Pageable pageable) {
        // Fetch users by role with pagination
        Page<UserDetail> userDetailsPage = userRepository.findByRole(userRole, pageable);

        // Convert the Page<UserDetail> to Page<UserDTO> using the userMapper
        return userDetailsPage.map(userListMapper::toDTO);
    }

    @Override
    public boolean existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void changePassword(Long id, String newPassword) {
       // Find the user by id, throw an exception if not found
        UserDetail userDetail = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        // Validate the new password to prevent SQL injection
        if (newPassword.matches(".*([';\\-\\-]).*")) {
            throw new IllegalArgumentException("Invalid password: potential SQL injection detected");
        }

        // Update the user's password
        userDetail.setPassword(newPassword);

        // Save the updated user to the repository
        userRepository.save(userDetail);
    }
}

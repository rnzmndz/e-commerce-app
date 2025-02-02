package net.renzo.userservice.controller;

import jakarta.validation.Valid;
import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.dto.UserDTO;
import net.renzo.userservice.dto.UserListDTO;
import net.renzo.userservice.dto.UserUpdateDTO;
import net.renzo.userservice.exception.UserAlreadyExistsException;
import net.renzo.userservice.exception.UserNotFoundException;
import net.renzo.userservice.model.Authority;
import net.renzo.userservice.model.UserRole;
import net.renzo.userservice.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing users.
 * <p>
 * This controller provides endpoints for creating, retrieving, updating, and deleting users.
 * It also includes endpoints for managing user authorities and checking user existence.
 * <p>
 * Base URL: /api/v1/users
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructs a new UserController with the specified UserService.
     *
     * @param userService the user service to be used by this controller
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user.
     *
     * @param userCreateDTO the user creation data transfer object
     * @return the created user data transfer object wrapped in a ResponseEntity
     * @throws UserAlreadyExistsException if a user with the given username already exists
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        // Check if a user with the given username already exists
        if (userService.existsByUsername(userCreateDTO.getUsername())) {
            // Throw an exception if the user already exists
            throw new UserAlreadyExistsException("User with username " + userCreateDTO.getUsername() + " already exists");
        }
        // Create the user and return the created user data transfer object wrapped in a ResponseEntity
        return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return the user data transfer object wrapped in a ResponseEntity
     * @throws UserNotFoundException if a user with the given ID is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        // Check if the user exists
        UserDTO userDTO = checkUserExists(id);

        // Return the user data transfer object wrapped in a ResponseEntity
        return ResponseEntity.ok(userDTO);
    }

    /**
     * Retrieves all users with pagination.
     *
     * @param pageable the pagination information
     * @return a page of user data transfer objects wrapped in a ResponseEntity
     */
    @GetMapping
    public ResponseEntity<Page<UserListDTO>> getAllUsers(Pageable pageable) {
        // Call the userService to get all users with pagination
        Page<UserListDTO> usersPage = userService.getAll(pageable);

        // Return the page of user data transfer objects wrapped in a ResponseEntity
        return ResponseEntity.ok(usersPage);
    }

    /**
     * Updates a user by their ID.
     *
     * @param id            the ID of the user
     * @param userUpdateDTO the user update data transfer object
     * @return the updated user data transfer object wrapped in a ResponseEntity
     * @throws UserNotFoundException if a user with the given ID is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        // Check if the user exists
        checkUserExists(id);

        // Update the user and return the updated user data transfer object wrapped in a ResponseEntity
        return ResponseEntity.ok(userService.updateById(id, userUpdateDTO));
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user
     * @return a ResponseEntity with no content
     * @throws UserNotFoundException if a user with the given ID is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // Check if the user exists
        checkUserExists(id);

        // Delete the user by ID
        userService.deleteById(id);

        // Return a ResponseEntity with no content
        return ResponseEntity.noContent().build();
    }

    /**
     * Adds authorities to a user by their ID.
     *
     * @param id the ID of the user
     * @param authorities the set of authorities to be added to the user
     * @return the updated user data transfer object wrapped in a ResponseEntity
     * @throws UserNotFoundException if a user with the given ID is not found
     */
    @PutMapping("/{id}/add-authorities")
    ResponseEntity<UserDTO> addAuthorities(@PathVariable Long id,
                                           @RequestBody Set<Authority> authorities){

        // Check if the user exists
        UserDTO userDTO = checkUserExists(id);

        // Add the authorities to the user
        userService.addAuthoritiesToUser(id, authorities);

        // Return the updated user data transfer object wrapped in a ResponseEntity
        return ResponseEntity.ok(userDTO);
    }

    /**
     * Removes an authority from a user by their ID.
     *
     * @param id the ID of the user
     * @param authority the authority to be removed from the user
     * @return the updated user data transfer object wrapped in a ResponseEntity
     * @throws UserNotFoundException if a user with the given ID is not found
     */
    @PutMapping("/{id}/remove-authorities")
    ResponseEntity<UserDTO> removeAuthority(@PathVariable Long id,
                                            @RequestBody Authority authority) {
        // Check if the user exists
        UserDTO userDTO = checkUserExists(id);

        // Remove the authority from the user
        userService.removeAuthorityFromUser(id, authority);

        // Return the updated user data transfer object wrapped in a ResponseEntity
        return ResponseEntity.ok(userDTO);
    }

    /**
     * Retrieves users by their role with pagination.
     *
     * @param role the role of the users
     * @param pageable the pagination information
     * @return a page of user data transfer objects wrapped in a ResponseEntity
     */
    @GetMapping("/role")
    public ResponseEntity<Page<UserListDTO>> getUsersByRole(@RequestBody UserRole role,
                                                            Pageable pageable) {
        // Call the userService to get users by role with pagination
        Page<UserListDTO> usersPage = userService.getUserByRole(role, pageable);

        // Return the page of user data transfer objects wrapped in a ResponseEntity
        return ResponseEntity.ok(usersPage);
    }

    /**
     * Checks if a user with the given username exists.
     *
     * @param username the username of the user
     * @return a boolean value indicating whether the user exists wrapped in a ResponseEntity
     */
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkUserExists(@RequestParam String username) {
        // Check if a user with the given username exists
        boolean userExists = userService.existsByUsername(username);

        // Return a boolean value indicating whether the user exists wrapped in a ResponseEntity
        return ResponseEntity.ok(userExists);
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id,
                                              @RequestParam String newPassword) {
        // Check if the user exists
        checkUserExists(id);

        // Change the password of the user
        userService.changePassword(id, newPassword);

        // Return a ResponseEntity with no content
        return ResponseEntity.noContent().build();
    }

    private UserDTO checkUserExists(Long id) {
        // Retrieve the user by ID
        Optional<UserDTO> userDTO = userService.getById(id);

        // Check if the user is present, if not throw an exception
        if (userDTO.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }

        return userDTO.get();
    }
}

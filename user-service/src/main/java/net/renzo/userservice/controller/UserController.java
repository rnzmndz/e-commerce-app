package net.renzo.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.dto.UserDTO;
import net.renzo.userservice.dto.UserListDTO;
import net.renzo.userservice.dto.UserUpdateDTO;
import net.renzo.userservice.exception.UserAlreadyExistsException;
import net.renzo.userservice.exception.UserNotFoundException;
import net.renzo.userservice.model.UserRole;
import net.renzo.userservice.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
@Tag(name = "User", description = "User API")
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
    @Operation(summary = "Create a new user", description = "Create a new user with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created user"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
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
    @Operation(summary = "Get user by ID", description = "Retrieve the details of a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
            @ApiResponse(responseCode = "400", description = "Invalid user ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@Parameter(description = "ID of the user to retrieve", required = true) @PathVariable Long id) {
        // Retrieve the user by ID
        Optional<UserDTO> userDTO = userService.getById(id);

        // Check if the user is present, if not throw an exception
        if (userDTO.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }

        // Return the user data transfer object wrapped in a ResponseEntity
        return ResponseEntity.ok(userDTO.get());
    }

    /**
     * Retrieves all users with pagination.
     *
     * @param pageable the pagination information
     * @return a page of user data transfer objects wrapped in a ResponseEntity
     */
    @Operation(summary = "Get all users", description = "Retrieve a paginated list of all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users")
    })
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
    @Operation(summary = "Update user by ID", description = "Update the details of a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated user"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Parameter(description = "ID of the user to update", required = true) @PathVariable Long id,
                                              @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        // Retrieve the user by ID
        Optional<UserDTO> userDTO = userService.getById(id);

        // Check if the user is present, if not throw an exception
        if (userDTO.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }

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
    @Operation(summary = "Delete user by ID", description = "Delete a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted user"),
            @ApiResponse(responseCode = "400", description = "Invalid user ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "ID of the user to delete", required = true) @PathVariable Long id) {
        // Retrieve the user by ID
        Optional<UserDTO> userDTO = userService.getById(id);

        // Check if the user is present, if not throw an exception
        if (userDTO.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }

        // Delete the user by ID
        userService.deleteById(id);

        // Return a ResponseEntity with no content
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves users by their role with pagination.
     *
     * @param role the role of the users
     * @param pageable the pagination information
     * @return a page of user data transfer objects wrapped in a ResponseEntity
     */

    @Operation(summary = "Get users by role", description = "Retrieve a paginated list of users by their role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users by role")
    })
    @GetMapping("/role")
    public ResponseEntity<Page<UserListDTO>> getUsersByRole(@Parameter(description = "Role of the users to retrieve", required = true)
                                                            @RequestParam UserRole role,
                                                            @Parameter(description = "Pagination information",
                                                                    schema = @Schema(implementation = Pageable.class,
                                                                            example = "{\"page\":0,\"size\":10,\"sort\":[\"username,asc\"]}"))
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
    @Operation(summary = "Check if user exists by username", description = "Check if a user with the given username exists.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully checked user existence"),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content)
    })
    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Boolean> checkUserExists(@Parameter(description = "Username of the user to check", required = true) @PathVariable String username) {
        // Check if a user with the given username exists
        boolean userExists = userService.existsByUsername(username);

        // Return a boolean value indicating whether the user exists wrapped in a ResponseEntity
        return ResponseEntity.ok(userExists);
    }

    /**
     * Checks if a user with the given email exists.
     *
     * @param email the email of the user
     * @return a boolean value indicating whether the user exists wrapped in a ResponseEntity
     */
    @Operation(summary = "Check if user exists by email", description = "Check if a user with the given email exists.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully checked user existence"),
            @ApiResponse(responseCode = "400", description = "Invalid email supplied", content = @Content)
    })
    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> checkUserExistsByEmail(@Parameter(description = "Email of the user to check", required = true) @PathVariable String email) {
        // Check if a user with the given email exists
        boolean userExists = userService.existsByEmail(email);

        // Return a boolean value indicating whether the user exists wrapped in a ResponseEntity
        return ResponseEntity.ok(userExists);
    }

    /**
     * Changes the password of a user by their ID.
     *
     * @param id the ID of the user
     * @param newPassword the new password for the user
     * @return a ResponseEntity with no content
     * @throws UserNotFoundException if a user with the given ID is not found
     */
    @Operation(summary = "Change user password", description = "Change the password of a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully changed password"),
            @ApiResponse(responseCode = "400", description = "Invalid user ID or password supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PutMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@Parameter(description = "ID of the user to change password", required = true) @PathVariable Long id,
                                               @Parameter(description = "New password for the user", required = true) @RequestParam String newPassword) {
        // Retrieve the user by ID
        Optional<UserDTO> userDTO = userService.getById(id);

        // Check if the user is present, if not throw an exception
        if (userDTO.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }

        // Change the password of the user
        userService.changePassword(id, newPassword);

        // Return a ResponseEntity with no content
        return ResponseEntity.noContent().build();
    }
}

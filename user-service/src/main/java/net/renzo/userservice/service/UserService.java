package net.renzo.userservice.service;

import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.dto.UserDTO;
import net.renzo.userservice.dto.UserListDTO;
import net.renzo.userservice.dto.UserUpdateDTO;
import net.renzo.userservice.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service interface for managing users.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param userCreateDTO the user creation data transfer object
     * @return the created user data transfer object
     */
    UserDTO createUser(UserCreateDTO userCreateDTO);

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return the user data transfer object
     */
    Optional<UserDTO> getById(Long id);

    /**
     * Retrieves all users with pagination.
     *
     * @param pageable the pagination information
     * @return a page of user data transfer objects
     */
    Page<UserListDTO> getAll(Pageable pageable);

    /**
     * Updates a user by their ID.
     *
     * @param id the ID of the user
     * @return the updated user data transfer object
     */
    UserDTO updateById(Long id, UserUpdateDTO UserUpdateDTO);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user
     */
    void deleteById(Long id);

    /**
     * Retrieves users by their role with pagination.
     *
     * @param userRole the role of the users
     * @return a page of user data transfer objects
     */
    Page<UserListDTO> getUserByRole(UserRole userRole, Pageable pageable);

    /**
     * Checks if a user exists by their username.
     *
     * @param username the username of the user
     * @return true if the user exists, false otherwise
     */
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    /**
     * Changes the password of a user.
     *
     * @param id the ID of the user
     * @param newPassword the new password
     */
    void changePassword(Long id, String newPassword);
}
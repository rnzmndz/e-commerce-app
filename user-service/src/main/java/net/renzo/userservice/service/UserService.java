package net.renzo.userservice.service;

import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.dto.UserDTO;
import net.renzo.userservice.model.Authority;
import net.renzo.userservice.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

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
    UserDTO getById(Long id);

    /**
     * Retrieves all users with pagination.
     *
     * @param pageable the pagination information
     * @return a page of user data transfer objects
     */
    Page<UserDTO> getAll(Pageable pageable);

    /**
     * Updates a user by their ID.
     *
     * @param id the ID of the user
     * @return the updated user data transfer object
     */
    UserDTO updateById(Long id);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user
     */
    void deleteById(Long id);

    /**
     * Adds authorities to a user.
     *
     * @param id the ID of the user
     * @param authorities the set of authorities to add
     * @return the updated user data transfer object
     */
    UserDTO addAuthoritiesToUser(Long id, Set<Authority> authorities);

    /**
     * Removes an authority from a user.
     *
     * @param id the ID of the user
     * @param authority the authority to remove
     * @return the updated user data transfer object
     */
    UserDTO removeAuthorityFromUser(Long id, Authority authority);

    /**
     * Retrieves users by their role with pagination.
     *
     * @param userRole the role of the users
     * @return a page of user data transfer objects
     */
    Page<UserDTO> getUserByRole(UserRole userRole);

    /**
     * Checks if a user exists by their username or email.
     *
     * @param username the username of the user
     * @param email the email of the user
     * @return true if the user exists, false otherwise
     */
    boolean existsByUsernameOrEmail(String username, String email);

    /**
     * Changes the password of a user.
     *
     * @param id the ID of the user
     * @param newPassword the new password
     */
    void changePassword(Long id, String newPassword);
}
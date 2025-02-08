package net.renzo.userservice.service;

import net.renzo.userservice.dto.AuthorityDTO;

import java.util.Set;

/**
 * Service interface for managing authorities.
 */
public interface AuthorityService {

    /**
     * Creates a new authority.
     *
     * @param authorityDTO the authority data transfer object
     * @return the created authority data transfer object
     */
    AuthorityDTO createAuthority(AuthorityDTO authorityDTO);

    /**
     * Retrieves an authority by its ID.
     *
     * @param id the ID of the authority
     * @return the authority data transfer object
     */
    AuthorityDTO getAuthority(Long id);

    /**
     * Retrieves the authorities associated with a user.
     *
     * @param userId the ID of the user
     * @return a set of authority names
     */
    Set<String> getAuthoritiesByUser(Long userId);

    /**
     * Retrieves all authorities.
     *
     * @return a set of all authority data transfer objects
     */
    Set<AuthorityDTO> getAllAuthority();

    /**
     * Updates an existing authority.
     *
     * @param id the ID of the authority to update
     * @param authorityDTO the updated authority data transfer object
     * @return the updated authority data transfer object
     */
    AuthorityDTO updateAuthority(Long id, AuthorityDTO authorityDTO);

    /**
     * Deletes an authority by its ID.
     *
     * @param id the ID of the authority to delete
     */
    void deleteAuthority(Long id);

    /**
     * Adds authorities to a user.
     *
     * @param userId the ID of the user
     * @param authorities the set of authority names to add
     */
    void addAuthorities(Long userId, Set<String> authorities);

    /**
     * Removes authorities from a user.
     *
     * @param userId the ID of the user
     * @param authorities the set of authority names to remove
     */
    void removeAuthorities(Long userId, Set<String> authorities);
}

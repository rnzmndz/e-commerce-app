package net.renzo.userservice.controller;

import jakarta.validation.Valid;
import net.renzo.userservice.dto.AuthorityDTO;
import net.renzo.userservice.service.AuthorityService;
import net.renzo.userservice.exception.AuthorityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * REST controller for managing authorities.
 */
@RestController
@RequestMapping("api/v1/authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    /**
     * Constructs a new AuthorityController with the given AuthorityService.
     *
     * @param authorityService the service to manage authorities
     */
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * Creates a new Authority.
     *
     * @param authorityDTO the DTO containing the details of the authority to be created
     * @return the created Authority as a DTO
     */
    @PostMapping
    public ResponseEntity<AuthorityDTO> createAuthority(@Valid @RequestBody AuthorityDTO authorityDTO) {
        // Call the service to create a new authority
        AuthorityDTO createdAuthority = authorityService.createAuthority(authorityDTO);

        // Return a response with the created authority and HTTP status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthority);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorityDTO> getAuthority(@PathVariable Long id) {
        AuthorityDTO authorityDTO = authorityService.getAuthority(id);
        return authorityDTO != null ? ResponseEntity.ok(authorityDTO) : ResponseEntity.notFound().build();
    }

    /**
     * Retrieves the set of authority names associated with a specific user.
     *
     * @param userId the ID of the user whose authorities are to be retrieved
     * @return a ResponseEntity containing a set of authority names
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Set<String>> getAuthoritiesByUser(@PathVariable Long userId) {
        // Call the service to get authorities by user ID
        Set<String> authorities = authorityService.getAuthoritiesByUser(userId);

        // Return a response with the set of authority names and HTTP status 200 (OK)
        return ResponseEntity.ok(authorities);
    }

    /**
     * Retrieves all authorities.
     *
     * @return a ResponseEntity containing a set of AuthorityDTOs representing all authorities
     */
    @GetMapping
    public ResponseEntity<Set<AuthorityDTO>> getAllAuthorities() {
        // Call the service to get all authorities
        Set<AuthorityDTO> authorities = authorityService.getAllAuthority();

        // Return a response with the set of AuthorityDTOs and HTTP status 200 (OK)
        return ResponseEntity.ok(authorities);
    }

    /**
     * Updates an existing Authority.
     *
     * @param id the ID of the authority to update
     * @param authorityDTO the DTO containing the updated details of the authority
     * @return a ResponseEntity containing the updated AuthorityDTO and HTTP status 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<AuthorityDTO> updateAuthority(@PathVariable Long id, @Valid @RequestBody AuthorityDTO authorityDTO) {
        // Call the service to update the authority with the given ID and details from the DTO
        AuthorityDTO updatedAuthority = authorityService.updateAuthority(id, authorityDTO);

        // Return a response with the updated authority and HTTP status 200 (OK)
        return ResponseEntity.ok(updatedAuthority);
    }

   /**
     * Deletes an existing Authority.
     *
     * @param id the ID of the authority to delete
     * @return a ResponseEntity with HTTP status 204 (No Content)
     * @throws AuthorityNotFoundException if the authority with the given ID is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthority(@PathVariable Long id) {
        // Call the service to delete the authority with the given ID
        authorityService.deleteAuthority(id);

        // Return a response with HTTP status 204 (No Content)
        return ResponseEntity.noContent().build();
    }

    /**
     * Adds a set of authorities to a user.
     *
     * @param userId      the ID of the user to whom the authorities are to be added
     * @param authorities the set of authority names to be added to the user
     * @return a ResponseEntity with HTTP status 204 (No Content)
     */
    @PostMapping("/user/{userId}/add")
    public ResponseEntity<Void> addAuthorities(@PathVariable Long userId, @RequestBody Set<String> authorities) {
        // Call the service to add authorities to the user with the given ID
        authorityService.addAuthorities(userId, authorities);

        // Return a response with HTTP status 204 (No Content)
        return ResponseEntity.noContent().build();
    }

    /**
     * Removes a set of authorities from a user.
     *
     * @param userId      the ID of the user from whom the authorities are to be removed
     * @param authorities the set of authority names to be removed from the user
     * @return a ResponseEntity with HTTP status 204 (No Content)
     */
    @PostMapping("/user/{userId}/remove")
    public ResponseEntity<Void> removeAuthorities(@PathVariable Long userId, @RequestBody Set<String> authorities) {
        // Call the service to remove authorities from the user with the given ID
        authorityService.removeAuthorities(userId, authorities);

        // Return a response with HTTP status 204 (No Content)
        return ResponseEntity.noContent().build();
    }
}

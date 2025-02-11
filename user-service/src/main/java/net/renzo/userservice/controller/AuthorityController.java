package net.renzo.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authority", description = "Authority API")
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
    @Operation(summary = "Create a new Authority", description = "Creates a new authority with the provided details.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created authority"),
        @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
        @ApiResponse(responseCode = "409", description = "Authority already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<AuthorityDTO> createAuthority(@Valid @RequestBody AuthorityDTO authorityDTO) {
        // Call the service to create a new authority
        AuthorityDTO createdAuthority = authorityService.createAuthority(authorityDTO);

        // Return a response with the created authority and HTTP status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthority);
    }

    @Operation(summary = "Get Authority by ID", description = "Retrieve the details of an authority by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved authority"),
        @ApiResponse(responseCode = "400", description = "Invalid authority ID supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Authority not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuthorityDTO> getAuthority(@Parameter(description = "ID of the authority to retrieve", required = true) @PathVariable Long id) {
        AuthorityDTO authorityDTO = authorityService.getAuthority(id);
        return authorityDTO != null ? ResponseEntity.ok(authorityDTO) : ResponseEntity.notFound().build();
    }

    /**
     * Retrieves the set of authority names associated with a specific user.
     *
     * @param userId the ID of the user whose authorities are to be retrieved
     * @return a ResponseEntity containing a set of authority names
     */
    @Operation(summary = "Get Authorities by User ID", description = "Retrieve the set of authority names associated with a specific user by their ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved authorities"),
        @ApiResponse(responseCode = "400", description = "Invalid user ID supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<Set<String>> getAuthoritiesByUser(@Parameter(description = "ID of the user to retrieve authorities for", required = true) @PathVariable Long userId) {
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
    @Operation(summary = "Get all Authorities", description = "Retrieve a list of all authorities.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of authorities")
    })
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
    @Operation(summary = "Update an existing Authority", description = "Updates the details of an existing authority by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated authority"),
        @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
        @ApiResponse(responseCode = "404", description = "Authority not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<AuthorityDTO> updateAuthority(@Parameter(description = "ID of the authority to update", required = true) @PathVariable Long id, @Valid @RequestBody AuthorityDTO authorityDTO) {
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
    @Operation(summary = "Delete an existing Authority", description = "Deletes the authority with the specified ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successfully deleted authority"),
        @ApiResponse(responseCode = "400", description = "Invalid authority ID supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Authority not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthority(@Parameter(description = "ID of the authority to delete", required = true) @PathVariable Long id) {
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
    @Operation(summary = "Add Authorities to User", description = "Adds a set of authorities to a user by their ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successfully added authorities to user"),
        @ApiResponse(responseCode = "400", description = "Invalid user ID or authorities supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PostMapping("/user/{userId}/add")
    public ResponseEntity<Void> addAuthorities(@Parameter(description = "ID of the user to add authorities", required = true) @PathVariable Long userId, @RequestBody Set<String> authorities) {
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
    @Operation(summary = "Remove Authorities from User", description = "Removes a set of authorities from a user by their ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successfully removed authorities from user"),
        @ApiResponse(responseCode = "400", description = "Invalid user ID or authorities supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PostMapping("/user/{userId}/remove")
    public ResponseEntity<Void> removeAuthorities(@Parameter(description = "ID of the user to remove authorities", required = true) @PathVariable Long userId, @RequestBody Set<String> authorities) {
        // Call the service to remove authorities from the user with the given ID
        authorityService.removeAuthorities(userId, authorities);

        // Return a response with HTTP status 204 (No Content)
        return ResponseEntity.noContent().build();
    }
}

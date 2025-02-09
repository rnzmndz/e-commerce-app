package net.renzo.userservice.service;

import jakarta.transaction.Transactional;
import net.renzo.userservice.dto.AuthorityDTO;
import net.renzo.userservice.exception.AuthorityNotFoundException;
import net.renzo.userservice.exception.UserNotFoundException;
import net.renzo.userservice.mapper.AuthorityMapper;
import net.renzo.userservice.model.Authority;
import net.renzo.userservice.model.UserDetail;
import net.renzo.userservice.repository.AuthorityRepository;
import net.renzo.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    private final AuthorityMapper authorityMapper;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository, UserRepository userRepository, AuthorityMapper AuthorityMapper) {
        this.authorityRepository = authorityRepository;
        this.authorityMapper = AuthorityMapper;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new Authority.
     *
     * @param authorityDTO the DTO containing the details of the authority to be created
     * @return the created Authority as a DTO
     */
    @Transactional
    @Override
    public AuthorityDTO createAuthority(AuthorityDTO authorityDTO) {
        // Convert the AuthorityDTO to an Authority entity
        Authority authority = authorityMapper.toEntity(authorityDTO);

        // Save the Authority entity to the repository
        authority = authorityRepository.save(authority);

        // Convert the saved Authority entity back to a DTO and return it
        return authorityMapper.toDTO(authority);
    }

    /**
     * Retrieves an Authority by its ID.
     *
     * @param id the ID of the authority to retrieve
     * @return the AuthorityDTO if found, otherwise null
     */
    @Override
    public AuthorityDTO getAuthority(Long id) {
        // Find the Authority by ID from the repository
        return authorityRepository.findById(id)
                // Map the Authority entity to a DTO if found
                .map(authorityMapper::toDTO)
                // Return null if the Authority is not found
                .orElse(null);
    }

    /**
     * Retrieves the set of authority names associated with a specific user.
     *
     * @param userId the ID of the user whose authorities are to be retrieved
     * @return a set of authority names
     */
    @Override
    public Set<String> getAuthoritiesByUser(Long userId) {
        // Find all Authority entities associated with the given user ID
        return authorityRepository.findAllByUsers_Id(userId).stream()
                // Map each Authority entity to its name
                .map(Authority::getName)
                // Collect the names into a set
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves all authorities.
     *
     * @return a set of AuthorityDTOs representing all authorities
     */
    @Override
    public Set<AuthorityDTO> getAllAuthority() {
        // Retrieve all Authority entities from the repository
        return authorityRepository.findAll().stream()
                // Map each Authority entity to an AuthorityDTO
                .map(authorityMapper::toDTO)
                // Collect the AuthorityDTOs into a set
                .collect(Collectors.toSet());
    }

    /**
     * Updates an existing Authority.
     *
     * @param id           the ID of the authority to update
     * @param authorityDTO the DTO containing the updated details of the authority
     * @return the updated Authority as a DTO
     * @throws AuthorityNotFoundException if the authority with the given ID is not found
     */
    @Transactional
    @Override
    public AuthorityDTO updateAuthority(Long id, AuthorityDTO authorityDTO) {
        // Find the existing Authority by ID
        Authority existingAuthority = authorityRepository.findById(id)
                .orElseThrow(() -> new AuthorityNotFoundException("Authority not found"));

        // Update the existing Authority with values from the DTO
        authorityMapper.updateFromDTO(authorityDTO, existingAuthority);

        // Save the updated Authority entity to the repository
        Authority updatedAuthority = authorityRepository.save(existingAuthority);

        // Convert the updated Authority entity back to a DTO and return it
        return authorityMapper.toDTO(updatedAuthority);
    }

    /**
     * Deletes an existing Authority.
     *
     * @param id the ID of the authority to delete
     * @throws AuthorityNotFoundException if the authority with the given ID is not found
     */
    @Transactional
    @Override
    public void deleteAuthority(Long id) {
        // Find the existing Authority by ID
        Authority existingAuthority = authorityRepository.findById(id)
                .orElseThrow(() -> new AuthorityNotFoundException("Authority not found"));

        // Delete the Authority entity from the repository
        authorityRepository.delete(existingAuthority);
    }

    /**
     * Adds a set of authorities to a user.
     *
     * @param userId      the ID of the user to whom the authorities are to be added
     * @param authorities the set of authority names to be added to the user
     * @throws UserNotFoundException      if the user with the given ID is not found
     * @throws AuthorityNotFoundException if any of the authorities are not found
     */
    @Transactional
    @Override
    public void addAuthorities(Long userId, Set<String> authorities) {
        // Find the user by ID
        UserDetail user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Convert authority names to Authority entities
        Set<Authority> authorityEntities = authorities.stream()
                .map(name -> authorityRepository.findByName(name)
                        .orElseThrow(() -> new AuthorityNotFoundException("Authority not found: " + name)))
                .collect(Collectors.toSet());

        // Add the user to the authorities then save the updated authority entity
        authorityEntities.forEach(authority -> authority.addUser(user));
        authorityRepository.saveAll(authorityEntities);
    }

    /**
     * Removes a set of authorities from a user.
     *
     * @param userId      the ID of the user from whom the authorities are to be removed
     * @param authorities the set of authority names to be removed from the user
     * @throws UserNotFoundException      if the user with the given ID is not found
     * @throws AuthorityNotFoundException if any of the authorities are not found
     */
    @Transactional
    @Override
    public void removeAuthorities(Long userId, Set<String> authorities) {
        // Find the user by ID
        UserDetail user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Convert authority names to Authority entities
        Set<Authority> authorityEntities = authorities.stream()
                .map(name -> authorityRepository.findByName(name)
                        .orElseThrow(() -> new AuthorityNotFoundException("Authority not found: " + name)))
                .collect(Collectors.toSet());

        // Remove the user from the authorities then save the updated authority entity
        authorityEntities.forEach(authority -> authority.removeUser(user));
        authorityRepository.saveAll(authorityEntities);
    }
}

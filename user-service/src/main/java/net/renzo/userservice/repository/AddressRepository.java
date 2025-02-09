package net.renzo.userservice.repository;

import net.renzo.userservice.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {

    /**
     * Retrieves the address associated with a user by user ID.
     *
     * @param userId the ID of the user whose address is being retrieved
     * @return the retrieved Address entity
     */
    Optional<Address> findByUserId(Long userId);
}

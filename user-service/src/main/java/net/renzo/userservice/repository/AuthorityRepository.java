package net.renzo.userservice.repository;

import net.renzo.userservice.model.Authority;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorityRepository extends ListCrudRepository<Authority, Long> {
    Optional<Authority> findByName(String name);
    List<Authority> findAllByUsers_Id(Long userId);
}

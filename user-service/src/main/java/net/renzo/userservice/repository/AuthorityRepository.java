package net.renzo.userservice.repository;

import net.renzo.userservice.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByName(String name);
    List<Authority> findAllByUserId(Long userId);

}

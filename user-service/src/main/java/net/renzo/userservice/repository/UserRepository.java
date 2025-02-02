package net.renzo.userservice.repository;

import net.renzo.userservice.model.UserDetail;
import net.renzo.userservice.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserDetail, Long> {

    boolean existsByUsernameOrEmail(String username, String email);
    boolean existsByUsername(String username);

    Page<UserDetail> findByRole(UserRole role, Pageable pageable);
}

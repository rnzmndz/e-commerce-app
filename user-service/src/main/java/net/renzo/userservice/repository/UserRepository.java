package net.renzo.userservice.repository;

import net.renzo.userservice.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetail, Long> {

    boolean existsByUsernameOrEmail(String username, String email);
}

package net.renzo.repository;

import net.renzo.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}

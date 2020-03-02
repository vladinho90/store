package spring.security.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.security.fullstack.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String username);
}
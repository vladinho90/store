package spring.security.fullstack.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.security.fullstack.model.Order;
import spring.security.fullstack.model.User;
import spring.security.fullstack.model.enums.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);

    Optional<Order> findAllByStatus(Status status);
}

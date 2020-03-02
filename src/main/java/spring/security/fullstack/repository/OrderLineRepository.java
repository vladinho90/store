package spring.security.fullstack.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.security.fullstack.model.Order;
import spring.security.fullstack.model.OrderLine;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    List<OrderLine> findAllByOrder(Order order);
}

package spring.security.fullstack.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.security.fullstack.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}

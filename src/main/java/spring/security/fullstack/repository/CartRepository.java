package spring.security.fullstack.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.security.fullstack.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

     void deleteById(Long id);
}

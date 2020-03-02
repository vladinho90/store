package spring.security.fullstack.service;


import spring.security.fullstack.model.Order;
import spring.security.fullstack.model.OrderLine;
import spring.security.fullstack.model.Product;
import spring.security.fullstack.model.User;
import spring.security.fullstack.model.enums.Status;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

    void save(Order order);

    List<Order> findAll();

    Order findById(Long id);

    void delete(Long id);

    Order update(Long id, Order order);

    List<Order> findAllOrdersByUser(User user);

    OrderLine createOrderLine(Product product, Order order, Integer quantity);

    Optional<Order> findOrderByStatus(Status status);
}

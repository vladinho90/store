package spring.security.fullstack.service;


import spring.security.fullstack.model.Order;
import spring.security.fullstack.model.OrderLine;

import java.util.List;

public interface IOrderLineService {

    void save(OrderLine orderLine);

    List<OrderLine> findAll();

    OrderLine findById(Long id);

    void delete(Long id);



    List<OrderLine> findAllOrderLinesByOrder(Order order);
}

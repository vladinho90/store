package spring.security.fullstack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.fullstack.model.Order;
import spring.security.fullstack.model.OrderLine;
import spring.security.fullstack.repository.OrderLineRepository;
import spring.security.fullstack.service.IOrderLineService;

import java.util.List;

@Service
public class OrderLineServiceImpl implements IOrderLineService {

    @Autowired
    public OrderLineRepository orderLineRepository;

    @Override
    public void save(OrderLine orderLine){
        orderLineRepository.save(orderLine);
    }

    @Override
    public List<OrderLine> findAll(){
        return orderLineRepository.findAll();
    }

    @Override
    public OrderLine findById(Long id){
        return orderLineRepository.findById(id).orElseThrow(()->new RuntimeException("OrderLine with id: "+id+" not found"));
    }

    @Override
    public void delete(Long id){
        orderLineRepository.deleteById(id);
    }


    @Override
    public List<OrderLine> findAllOrderLinesByOrder(Order order){
       return orderLineRepository.findAllByOrder(order);
    }
}

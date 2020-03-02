package spring.security.fullstack.dto.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.security.fullstack.dto.order_line.OrderLineMapper;
import spring.security.fullstack.dto.order_line.OrderLineResponse;
import spring.security.fullstack.model.Order;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    public OrderLineMapper orderLineMapper;

    public OrderResponse toDto(Order order) {
        OrderResponse dto = new OrderResponse();
        dto.setId(order.getId());
        dto.setUser(order.getUser());
        dto.setDeliveryAddress(order.getDeliveryAddress());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());

        List<OrderLineResponse> orderLineResponses = order.getOrderLineList()
                .stream()
                .map(orderLineMapper::toDto)
                .collect(Collectors.toList());
        dto.setOrderLineResponsesList(orderLineResponses);
        return dto;
    }

    public List<OrderResponse> toDto(List<Order> orderList) {
        return orderList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

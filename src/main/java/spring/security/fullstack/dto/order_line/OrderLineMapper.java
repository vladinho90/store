package spring.security.fullstack.dto.order_line;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.security.fullstack.dto.product.ProductMapper;
import spring.security.fullstack.dto.product.ProductResponse;
import spring.security.fullstack.model.OrderLine;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderLineMapper {

    @Autowired
    public ProductMapper productMapper;

    public OrderLineResponse toDto(OrderLine orderLine) {
        OrderLineResponse dto = new OrderLineResponse();
        dto.setId(orderLine.getId());
        ProductResponse productResponse = productMapper.toDto(orderLine.getProduct());
        dto.setProductResponse(productResponse);
        dto.setQuantity(orderLine.getQuantity());
        dto.setPrice(orderLine.getPrice());
        return dto;
    }

    public List<OrderLineResponse> toDto(List<OrderLine> orderLines) {
        return orderLines.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

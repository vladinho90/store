package spring.security.fullstack.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.fullstack.dto.order.OrderMapper;
import spring.security.fullstack.dto.order.OrderResponse;
import spring.security.fullstack.dto.order_line.OrderLineMapper;
import spring.security.fullstack.dto.order_line.OrderLineResponse;
import spring.security.fullstack.model.Order;
import spring.security.fullstack.model.User;
import spring.security.fullstack.repository.OrderLineRepository;
import spring.security.fullstack.service.IOrderLineService;
import spring.security.fullstack.service.IUserService;
import spring.security.fullstack.service.impl.CartServiceImpl;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(CartController.API_CART)
public class CartController {

    public static final String API_CART = "/cart";

    @Autowired
    public IUserService userService;

    @Autowired
    public IOrderLineService orderLineService;

    @Autowired
    public OrderLineMapper orderLineMapper;

    @Autowired
    public OrderLineRepository orderLineRepository;

    @Autowired
    public CartServiceImpl cartService;

    @Autowired
    public OrderMapper orderMapper;



    @GetMapping()
    public ResponseEntity<List<OrderLineResponse>> getOrdersLine(Principal principal){
        User user = userService.findByUsername(principal.getName());
        return null;

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getCart(@PathVariable (name = "id") Long cartId){
        Order order = cartService.checkout(cartId);
        OrderResponse orderResponses = orderMapper.toDto(order);
        return new ResponseEntity<>(orderResponses, HttpStatus.OK);
    }
}

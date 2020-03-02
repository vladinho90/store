package spring.security.fullstack.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.fullstack.model.*;
import spring.security.fullstack.model.enums.Status;
import spring.security.fullstack.repository.CartItemRepository;
import spring.security.fullstack.repository.CartRepository;
import spring.security.fullstack.repository.OrderRepository;
import spring.security.fullstack.service.ICartService;
import spring.security.fullstack.service.IOrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    public IOrderService orderService;

    @Autowired
    public CartServiceImpl cartService;

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public CartItemRepository cartItemRepository;

    @Autowired
    public ProductServiceImpl productService;

    @Autowired
    public OrderRepository orderRepository;

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    public void delete(Long cartItemId, User user) {
        Optional<CartItem> cartItem = getCart(user).getCartItemSet()
                .stream().filter(item -> cartItemId.equals(item.getProduct().getId())).findFirst();
        cartItem.ifPresent(productInOrder -> {
            productInOrder.setCart(null);
            cartRepository.deleteById(productInOrder.getId());
        });
    }

    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }


    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart findCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("cart with id " + id + " not found"));
    }

    //add products in the user cart
    @Override
    public void addToCart(CartItem cartItem, User user) {
        Cart finalCart = user.getCart();
        Set<CartItem> set = finalCart.getCartItemSet();
        Optional<CartItem> old = set.stream().filter(item -> item.getProduct().getId().equals(cartItem.getProduct().getId())).findFirst();
        CartItem item;
        if (old.isPresent()) {
            item = old.get();
            item.setQuantity(cartItem.getQuantity() + item.getQuantity());
        } else {
            item = cartItem;
            item.setCart(finalCart);
            finalCart.getCartItemSet().add(item);
        }
        cartItemRepository.save(item);

        cartRepository.save(finalCart);
    }


    @Override
    public void deleteCart(Long cartItemId, User user) {
        Optional<CartItem> cartItemOptional = user.getCart().getCartItemSet().stream().filter(itemId -> cartItemId.equals(itemId.getId())).findFirst();
        cartItemOptional.ifPresent(cartItem -> {
            cartItem.setCart(null);
            cartItemRepository.deleteById(cartItem.getId());
        });
    }

    //when user clicked on the checkout button we have to decrease stock for all the products
    //convert all cart items in order lines, if we change the price after user checkout, we don't change
    //the price for the products which have already been ordered
    @Override
    public Order checkout(Long cartId) {
        Order order = new Order();
        order.setStatus(Status.NEW);

        Cart cart = findCartById(cartId);

        List<OrderLine> orderLineList = cart.getCartItemSet().stream()
                .map(cartItem -> {
                    OrderLine orderLine = new OrderLine();
                    orderLine.setProduct(cartItem.getProduct());
                    orderLine.setOrder(order);
                    orderLine.setPrice(getPricePerOrderLine(cartItem));
                    orderLine.setQuantity(cartItem.getQuantity());

                    return orderLine;
                })
                .collect(Collectors.toList());


        orderLineList.forEach(orderLine ->
                productService.decreaseStock(orderLine.getProduct().getId(), orderLine.getQuantity()));

        BigDecimal totalPrice = orderLineList.stream()
                .map(OrderLine::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        order.setTotalPrice(totalPrice);

        order.setOrderLineList(orderLineList);
        orderRepository.save(order);

        cart.deleteAllItems();

        return order;
    }


    //calculate the order price
    @Override
    public BigDecimal getPricePerOrderLine(CartItem cartItem) {
        BigDecimal totalPrice = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        return totalPrice;
    }
}

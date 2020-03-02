package spring.security.fullstack.service;


import spring.security.fullstack.model.Cart;
import spring.security.fullstack.model.CartItem;
import spring.security.fullstack.model.Order;
import spring.security.fullstack.model.User;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCart(User user);

    void addToCart(CartItem cartItem, User user);

    void deleteCart(Long cartItemId, User user);

    void delete(Long cartItemId, User user);

    void saveCart(Cart cart);

    Order checkout(Long cartId);

    void deleteCartById(Long id);

    Cart findCartById(Long id);

    BigDecimal getPricePerOrderLine(CartItem cartItem);
}

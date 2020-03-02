package spring.security.fullstack.service;


import spring.security.fullstack.model.CartItem;

import java.util.List;

public interface ICartItemService {


    void saveCartItem(CartItem cartItem);

    List<CartItem> findAllCartItems();

    CartItem findCartItemById(Long id);

    void deleteCartItemById(Long id);

    CartItem updateCartItem(Long id, CartItem cartItem);
}

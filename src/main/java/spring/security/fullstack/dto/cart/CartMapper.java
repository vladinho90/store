package spring.security.fullstack.dto.cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.security.fullstack.dto.cart_item.CartItemMapper;
import spring.security.fullstack.dto.cart_item.CartItemResponse;
import spring.security.fullstack.model.Cart;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    public CartItemMapper cartItemMapper;

    public CartResponse toDto(Cart cart){
        CartResponse dto = new CartResponse();
        dto.setId(cart.getId());
        dto.setUser(cart.getUser());
        Set<CartItemResponse> cartItemResponses = cart.getCartItemSet()
                .stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toSet());
        dto.setCartItemResponses(cartItemResponses);
        return dto;
    }
}

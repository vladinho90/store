package spring.security.fullstack.dto.cart_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.security.fullstack.dto.product.ProductMapper;
import spring.security.fullstack.dto.product.ProductResponse;
import spring.security.fullstack.model.CartItem;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartItemMapper {

    @Autowired
    public ProductMapper productMapper;

    public CartItemResponse toDto(CartItem cartItem){
        CartItemResponse dto = new CartItemResponse();
        dto.setId(cartItem.getId());
        ProductResponse productResponse = productMapper.toDto(cartItem.getProduct());
        dto.setProductResponse(productResponse);
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }

    public List<CartItemResponse> toDto(List<CartItem> cartItems){
        return cartItems.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

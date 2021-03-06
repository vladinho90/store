package spring.security.fullstack.dto.cart_item;


import spring.security.fullstack.dto.product.ProductResponse;

public class CartItemResponse {

    private Long id;
    private ProductResponse productResponse;
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductResponse getProductResponse() {
        return productResponse;
    }

    public void setProductResponse(ProductResponse productResponse) {
        this.productResponse = productResponse;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

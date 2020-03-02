package spring.security.fullstack.service;

import spring.security.fullstack.model.Product;
import spring.security.fullstack.model.enums.Category;

import java.util.List;

public interface IProductService {

    void save(Product product);

    //ProductResponse saveProduct(ProductRequest request);

    List<Product> findAll();

    Product findById(Long id);

    void delete(Long id);

    Product update(Long id, Product product);

    List<Product> findAllProductsByCategory(Category category);

    void increaseStock(Long productId, int quantity);

    void decreaseStock(Long productId, int quantity);

    String setProductUrl(Category category, Long id, String title);


}

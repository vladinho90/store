package spring.security.fullstack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.fullstack.model.Product;
import spring.security.fullstack.model.enums.Category;
import spring.security.fullstack.repository.ProductRepository;
import spring.security.fullstack.service.IProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    public ProductRepository productRepository;

    public String capitalizeFirstLatter(String string){
       return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
    }

    @Override
    public void save(Product product) {
        product.setPictureURL(setProductUrl(product.getCategory(),product.getId(),product.getTitle()));
        product.setTitle(capitalizeFirstLatter(product.getTitle()));
        product.setDescription(capitalizeFirstLatter(product.getDescription()));
        product.setStock(true);
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product with id " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product product) {
        Product productUpdated = findById(id);
        productUpdated.setCategory(product.getCategory());
        productUpdated.setPictureURL(product.getPictureURL());
        productUpdated.setPrice(product.getPrice());
        productUpdated.setUnitsInStock(product.getUnitsInStock());
        productUpdated.setStock(product.isStock());
        productUpdated.setTitle(product.getTitle());
        return productUpdated;
    }

    @Override
    public List<Product> findAllProductsByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    //increase stock, if order was declined or ADMIN change the quantity of a product
    @Override
    public void increaseStock(Long productId, int quantity) {
        Product product = findById(productId);
        if (product == null) {
            throw new RuntimeException("Product with id: " + productId + " not found");
        }
        int updateStock = product.getUnitsInStock() + quantity;
        product.setUnitsInStock(updateStock);
        update(productId,product);
        productRepository.save(product);
    }

    //decrease stock when user click on the checkout button
    @Override
    public void decreaseStock(Long productId, int quantity) {
        Product product = findById(productId);
        if (product == null) {
            throw new RuntimeException("Product with id: " + productId + " not found");
        }
        int updateStock = product.getUnitsInStock() - quantity;
        if (updateStock == 0) {
            product.setStock(false);
        } else if (updateStock < 0) {
            throw new RuntimeException("Product not enough");
        }
        product.setUnitsInStock(updateStock);
        update(productId,product);
        productRepository.save(product);
    }

    //set product url in our frontend app
    @Override
    public String setProductUrl(Category category, Long id,String title) {
        String titleWithoutWiteSpace=title.replaceAll("\\s+","").toLowerCase();
        return "assets\\images\\products\\"+category.toString()+"\\"+titleWithoutWiteSpace+".png";
    }
}

package spring.security.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.security.fullstack.model.Product;
import spring.security.fullstack.model.enums.Category;
import spring.security.fullstack.repository.ProductRepository;
import spring.security.fullstack.service.IProductService;

import java.util.List;

@RestController
@RequestMapping(CategoryController.API_CATEGORY)
public class CategoryController {
    public static final String API_CATEGORY = "/api/category";

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private IProductService productService;

    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam(name = "category") Category category){

        List<Product> products= productService.findAllProductsByCategory(category);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(){

        List<Category> productsCategory= productRepository.findAllCategory();

        return new ResponseEntity<>(productsCategory, HttpStatus.OK);
    }
}

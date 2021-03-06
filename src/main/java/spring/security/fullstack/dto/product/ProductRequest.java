package spring.security.fullstack.dto.product;


import spring.security.fullstack.model.enums.Category;

import java.math.BigDecimal;

public class ProductRequest {

    private Category category;
    private String title;
    private BigDecimal price;
    private boolean stock;
    private int unitsInStock;
    private String description;
//    private String pictureURL;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }
//
//    public String getPictureURL() {
//        return pictureURL;
//    }
//
//    public void setPictureURL(String pictureURL) {
//        this.pictureURL = pictureURL;
//    }
}

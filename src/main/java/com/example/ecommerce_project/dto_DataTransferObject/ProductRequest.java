package com.example.ecommerce_project.dto_DataTransferObject;

import com.example.ecommerce_project.Constant.ProductCategory;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProductRequest {


    @NotNull
    private String product_name;

    @NotNull
    private ProductCategory category;

    @NotNull
    private String image_url;

    @NotNull
    private int price;

    @NotNull
    private int stock;

    private String description;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

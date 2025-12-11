package com.example.ecommerce_project.Controller;


import com.example.ecommerce_project.Constant.ProductCategory;
import com.example.ecommerce_project.Service.ProductService;
import com.example.ecommerce_project.dto_DataTransferObject.ProductRequest;
import com.example.ecommerce_project.dto_DataTransferObject.RequestParameter;
import com.example.ecommerce_project.modle.Product;
import com.example.ecommerce_project.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        Product get_product = productService.getProductById(id);
        if (get_product  == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("現在還沒有id為 " + id + " 的商品喔TAT");
        }else  {
            return ResponseEntity.ok().body(get_product);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
              @RequestParam(required = false) ProductCategory category
            , @RequestParam(required = false) String name
            , @RequestParam(defaultValue = "created_date") String order
            , @RequestParam(defaultValue = "desc") String sort
            , @RequestParam(defaultValue = "3") @Max(10)@Min(1) Integer limit
            , @RequestParam(defaultValue = "0") @Min(0) Integer offset) {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setCategory(category);
        requestParameter.setName(name);
        requestParameter.setOrder(order);
        requestParameter.setSort(sort);
        requestParameter.setLimit(limit);
        requestParameter.setOffset(offset);

        Page<Product> page = new Page<Product>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(productService.countProducts(requestParameter));
        page.setResult(productService.getProducts(requestParameter));
        return ResponseEntity.ok().body(page);
    }

//    @GetMapping("/produts/findByCategory")
//    public ResponseEntity<?> getProductsByCategory(@RequestParam String category) {
//
//        ProductCategory.valueOf(category);//在此先確認有沒有這個category
//
//        List<Product> product_list = productService.getProductsByCategory(category);
//        if (product_list != null) {
//            return ResponseEntity.ok().body(product_list);
//        }else  {
//            return ResponseEntity.status(HttpStatus.OK).body("目前的category裡面沒有商品喔~");
//        }
//    }

    @PostMapping("/products")
    public ResponseEntity<?> postOne(@Valid @RequestBody ProductRequest product) {
        Integer product_id = productService.CreateProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.getProductById(product_id));
    }

    @PutMapping("/update/product/{id}")
    public ResponseEntity<?> tes(@PathVariable Integer id,@RequestBody Map<String,Object> map){
        Product get_product = productService.getProductById(id);
        if (get_product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("此id為 " + id + " 之商品不存在!");
        }

        productService.updateProduct(id, map);
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @DeleteMapping("/delete/product/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("已刪除id為 " + id + " 之Product~");
    }







}

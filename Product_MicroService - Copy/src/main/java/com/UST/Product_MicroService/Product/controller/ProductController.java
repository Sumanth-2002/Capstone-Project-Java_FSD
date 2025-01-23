package com.UST.Product_MicroService.Product.controller;

import com.UST.Product_MicroService.Product.model.Product;
import com.UST.Product_MicroService.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);

    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getCategoryById(id),HttpStatus.OK);
    }


    @PutMapping("/updateProductbyId")
    public ResponseEntity<Product> updateProductById(@RequestParam Long id,@RequestBody Product product){
        return new ResponseEntity<>(productService.updateCategoryById(id,product),HttpStatus.OK);
    }



    @GetMapping("/getAllProductnames")
    public ResponseEntity<List<String>> getAllProductNames(){
        return ResponseEntity.ok(productService.getAllProductNames());
    }
    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

}

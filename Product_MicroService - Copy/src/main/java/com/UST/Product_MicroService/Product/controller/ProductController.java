package com.UST.Product_MicroService.Product.controller;

import com.UST.Product_MicroService.Product.model.Category;
import com.UST.Product_MicroService.Product.model.Product;
import com.UST.Product_MicroService.Product.service.CategoryService;
import com.UST.Product_MicroService.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product,@RequestParam Long categoryId){
        return new ResponseEntity<>(productService.addProduct(product,categoryId), HttpStatus.OK);

    }

    @GetMapping("/getProductById")
    public ResponseEntity<Product> getProductById(@RequestParam Long id){
        return new ResponseEntity<>(productService.getCategoryById(id),HttpStatus.OK);
    }

//    @DeleteMapping("/deleteById")
//    public ResponseEntity<String> deleteProductById(@RequestParam Long id){
//        return new ResponseEntity<>(productService.deleteCustomerById(id),HttpStatus.OK);
//    }

    @PutMapping("/updateProductbyId")
    public ResponseEntity<Product> updateProductById(@RequestParam Long id,@RequestBody Product product){
        return new ResponseEntity<>(productService.updateCategoryById(id,product),HttpStatus.OK);
    }

    @Autowired
    private CategoryService categoryService;

    @GetMapping("category/{id}/products")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable int id) {
        List<Product> products = categoryService.getProductsByCategoryId(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.OK);
    }

    @GetMapping("/getCategoryById")
    public ResponseEntity<Category> getCategoryById(@RequestParam Long id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategoryById")
    public ResponseEntity<String> deleteCategoryById(@RequestParam Long id) {
        return new ResponseEntity<>(categoryService.deleteCategoryById(id), HttpStatus.OK);
    }

    @PutMapping("/updateCategoryById")
    public ResponseEntity<Category> updateCategoryById(@RequestParam Long id, @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.updateCategoryById(id, category), HttpStatus.OK);
    }

}

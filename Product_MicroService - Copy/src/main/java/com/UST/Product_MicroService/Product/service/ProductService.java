package com.UST.Product_MicroService.Product.service;

import com.UST.Product_MicroService.Product.model.Category;
import com.UST.Product_MicroService.Product.model.Product;
import com.UST.Product_MicroService.Product.repository.CategoryRepository;
import com.UST.Product_MicroService.Product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product addProduct(Product product,Long categoryId){
        Category category= categoryRepository.findById(categoryId).orElse(null);
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

    public Product getCategoryById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public String deleteCategoryById(Long id) {
        productRepository.deleteById(id);
        return "Customer Deleted Successfully";
    }

    public Product updateCategoryById(Long id,Product productDetails) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(productDetails.getName());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setCategory(productDetails.getCategory());
            existingProduct.setSellerId(productDetails.getSellerId());
            existingProduct.setUpdatedAt(productDetails.getUpdatedAt());
            existingProduct.setActive(productDetails.isActive());

            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

}

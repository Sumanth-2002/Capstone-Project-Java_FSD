package com.UST.Product_MicroService.Product.service;

import com.UST.Product_MicroService.Product.model.Product;
import com.UST.Product_MicroService.Product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product addProduct(Product product){

        return  productRepository.save(product);
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
            existingProduct.setUpdatedAt(productDetails.getUpdatedAt());
            existingProduct.setActive(productDetails.isActive());

            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }
    public List<String> getAllProductNames(){
        return productRepository.getAllProductName();
    }
    public List<Product> getAllProducts(){return productRepository.findAll();}
}

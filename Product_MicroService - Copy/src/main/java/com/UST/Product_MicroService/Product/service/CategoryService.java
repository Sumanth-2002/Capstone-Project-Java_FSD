package com.UST.Product_MicroService.Product.service;

import com.UST.Product_MicroService.Product.model.Category;
import com.UST.Product_MicroService.Product.model.Product;
import com.UST.Product_MicroService.Product.repository.CategoryRepository;
import com.UST.Product_MicroService.Product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Category addCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public String deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
        return "Category Deleted Successfully";
    }

    public Category updateCategoryById(Long id, Category categoryDetails) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(categoryDetails.getCategoryName());
            existingCategory.setUpdatedAt(categoryDetails.getUpdatedAt());

            return categoryRepository.save(existingCategory);
        } else {
            return null;
        }
    }

    public List<Product> getProductsByCategoryId(int categoryId) {
        return productRepository.findByCategory_CategoryId(categoryId);
    }
}

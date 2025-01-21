package com.UST.Product_MicroService.Product.repository;

import com.UST.Product_MicroService.Product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

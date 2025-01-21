package com.UST.Product_MicroService.Product.repository;

import com.UST.Product_MicroService.Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategory_CategoryId(int categoryId);

}

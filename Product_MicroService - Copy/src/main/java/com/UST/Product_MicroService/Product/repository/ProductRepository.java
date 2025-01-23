package com.UST.Product_MicroService.Product.repository;

import com.UST.Product_MicroService.Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p.name from Product p ORDER By p.name")
    List<String> getAllProductName();
}

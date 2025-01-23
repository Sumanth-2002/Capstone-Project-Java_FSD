package com.UST.Product_MicroService.Product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Product{
    public Product(Long productId, String name, double price , LocalDate updatedAt, boolean isActive,Category category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;

        this.updatedAt = updatedAt;
        this.isActive = isActive;
    }

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Id
    private Long productId;
    private String name;
    private double price;
    private LocalDate updatedAt;
    private boolean isActive;
    @ManyToOne
    @JsonIgnore
    private Category category;
}

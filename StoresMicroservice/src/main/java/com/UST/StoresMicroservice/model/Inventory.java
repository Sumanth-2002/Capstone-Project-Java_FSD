package com.UST.StoresMicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long inventoryId;
    private Long storeId;
    private Long productId;
    private int stock;
    private LocalDate lastUpdated;

    public Inventory() {
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Inventory(Long inventoryId,  Long storeId, Long productId, int stock, LocalDate lastUpdated) {
        this.inventoryId = inventoryId;
        this.storeId = storeId;
        this.productId = productId;
        this.stock = stock;
        this.lastUpdated = lastUpdated;
    }


//    @OneToOne
//    @JoinColumn(name = "store")
//    @JsonIgnore
//    private Store store;

//    public Store getStore() {
//        return store;
//    }
//
//    public void setStore(Store store) {
//        this.store = store;
//    }


}
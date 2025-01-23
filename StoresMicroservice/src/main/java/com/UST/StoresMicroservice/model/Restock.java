package com.UST.StoresMicroservice.model;

import jakarta.persistence.*;

@Entity
public class Restock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestId;
    private Long productId;
    private int quantity;
    private Long storeId;
    private String storeName;
    private String managerName;

    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    private Status status; // Use the specific enum type

    // Getters and Setters
    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Restock() {
    }

    public Restock( Long productId, int quantity, Long storeId, String storeName, String managerName, Status status) {

        this.productId = productId;
        this.quantity = quantity;
        this.storeId = storeId;
        this.storeName = storeName;
        this.managerName = managerName;
        this.status = status;
    }
}
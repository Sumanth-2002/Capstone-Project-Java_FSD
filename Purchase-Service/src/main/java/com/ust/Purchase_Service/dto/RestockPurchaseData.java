package com.ust.Purchase_Service.dto;

import java.util.Date;

public class RestockPurchaseData {

    private Long storeId;
    private Long productId;
    private int quantity;
    private Date purchaseDate;


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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public RestockPurchaseData() {
    }

    public RestockPurchaseData( Long storeId, Long productId, int quantity, Date purchaseDate) {

        this.storeId = storeId;
        this.productId = productId;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }
}

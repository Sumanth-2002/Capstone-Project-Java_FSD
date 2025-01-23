package com.ust.Purchase_Service.dto;

public class InventoryUpdateDto {
    private Long inventoryId;
    private Long productId;
    private int stock;

    public InventoryUpdateDto() {
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public InventoryUpdateDto(Long inventoryId, Long productId, int stock) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.stock = stock;
    }
}

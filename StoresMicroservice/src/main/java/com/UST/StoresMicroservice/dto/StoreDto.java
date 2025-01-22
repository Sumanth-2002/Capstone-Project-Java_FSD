package com.UST.StoresMicroservice.dto;

public class StoreDto {
    private String storeName;
    private Long storeId;

    public StoreDto(String storeName, Long storeId) {
        this.storeName = storeName;
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}

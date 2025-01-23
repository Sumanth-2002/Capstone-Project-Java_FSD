package com.ust.sales_service.dto;

public class StoreSalesDto {
    private String storeName;
    private Long storeId;
    private Double totalSale;

    public StoreSalesDto(String storeName, Long storeId, Double totalSale) {
        this.storeName = storeName;
        this.storeId = storeId;
        this.totalSale = totalSale;
    }

    public StoreSalesDto() {
    }

    public Double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(Double totalSale) {
        this.totalSale = totalSale;
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
}

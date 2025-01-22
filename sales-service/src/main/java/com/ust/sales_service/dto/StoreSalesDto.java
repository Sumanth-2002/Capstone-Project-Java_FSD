package com.ust.sales_service.dto;

public class StoreSalesDto {
    private String storeName;
    private Integer storeId;
    private Double totalSale;

    public StoreSalesDto(String storeName, Integer storeId, Double totalSale) {
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}

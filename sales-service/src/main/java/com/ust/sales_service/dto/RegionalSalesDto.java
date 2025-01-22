package com.ust.sales_service.dto;

public class RegionalSalesDto {
    private String region;
    private Double totalSale=0.0;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(Double totalSale) {
        this.totalSale = totalSale;
    }

    public RegionalSalesDto(String region, Double totalSale) {
        this.region = region;
        this.totalSale = totalSale;
    }

    public RegionalSalesDto() {
    }
}

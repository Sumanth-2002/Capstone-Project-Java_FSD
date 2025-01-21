package com.ust.seller_service.dto;


public class SellerDto {
    private Long sellerId;
    private String name;

    public SellerDto() {
    }
//
//    public SellerDto(String name, String sellerId) {
//        this.name = name;
//        this.sellerId = sellerId;
//    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.ust.sales_service.dto;

import java.util.Date;

public class SalesSummaryDto {
    private Date saleDate;
    private Double totalPrice;
    private Long totalTransactions;

    public SalesSummaryDto(Date saleDate, Double totalPrice, Long totalTransactions) {
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
        this.totalTransactions = totalTransactions;
    }

    // Getters and setters
    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(Long totalTransactions) {
        this.totalTransactions = totalTransactions;
    }
}

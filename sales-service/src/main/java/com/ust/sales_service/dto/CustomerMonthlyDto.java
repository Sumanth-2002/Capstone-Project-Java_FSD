package com.ust.sales_service.dto;

public class CustomerMonthlyDto {
    private Integer month;
    private Long noOfCustomers;

    public CustomerMonthlyDto() {
    }

    public CustomerMonthlyDto(Integer month, Long noOfCustomers) {
        this.month = month;
        this.noOfCustomers = noOfCustomers;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Long getNoOfCustomers() {
        return noOfCustomers;
    }

    public void setNoOfCustomers(Long noOfCustomers) {
        this.noOfCustomers = noOfCustomers;
    }
}

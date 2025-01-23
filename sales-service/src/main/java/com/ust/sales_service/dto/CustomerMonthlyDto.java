package com.ust.sales_service.dto;

public class CustomerMonthlyDto {
    private String month;
    private Long noOfCustomers;

    public CustomerMonthlyDto() {
    }

    public CustomerMonthlyDto(String month, Long noOfCustomers) {
        this.month = month;
        this.noOfCustomers = noOfCustomers;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getNoOfCustomers() {
        return noOfCustomers;
    }

    public void setNoOfCustomers(Long noOfCustomers) {
        this.noOfCustomers = noOfCustomers;
    }
}

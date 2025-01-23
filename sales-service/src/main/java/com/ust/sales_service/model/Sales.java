package com.ust.sales_service.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashMap;

@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long saleId;
    private Long storeId;
//    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    private Long productId;
    private int quantity;
//    private HashMap<Integer,Integer> productData;
    private Double totalPrice;
    private Date saleDate;
    private String paymentMethod;

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Sales() {
    }

    public Sales(Long storeId, Customer customer, Long productId, int quantity, Double totalPrice, Date saleDate, String paymentMethod) {
        this.storeId = storeId;
        this.customer = customer;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
        this.paymentMethod = paymentMethod;
    }
}

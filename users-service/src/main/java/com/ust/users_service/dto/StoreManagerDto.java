package com.ust.users_service.dto;


public class StoreManagerDto {
    private Long id;
    private String name;
    private Long storeId;

    // Constructors
    public StoreManagerDto() {}

    public StoreManagerDto(Long id, String name, Long storeId) {
        this.id = id;
        this.name = name;
        this.storeId = storeId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}

package com.ust.users_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "new-user")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    private String region;

    private Long storeId;

    // Constructors
    public UserInfo() {
    }

    public UserInfo(String name, String email, String password, Roles roles, String region, Long storeId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.region = (roles == Roles.REGIONAL_MANAGER) ? region : null;
        this.storeId = (roles == Roles.STORE_MANAGER) ? storeId : null;
    }

    public UserInfo(String name, String email, String password, Roles roles) {
        this(name, email, password, roles, null, null);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
        // Reset region and storeId based on role
        if (roles == Roles.ADMIN) {
            this.region = null;
            this.storeId = null;
        } else if (roles == Roles.REGIONAL_MANAGER) {
            this.storeId = null;
        } else if (roles == Roles.STORE_MANAGER) {
            this.region = null;
        }
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        if (this.roles == Roles.REGIONAL_MANAGER) {
            this.region = region;
        }
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        if (this.roles == Roles.STORE_MANAGER) {
            this.storeId = storeId;
        }
    }
}
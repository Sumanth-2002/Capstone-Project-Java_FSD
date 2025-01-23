package com.ust.users_service.controller;

import com.ust.users_service.dto.RegionalManagerDto;
import com.ust.users_service.dto.StoreManagerDto;
import com.ust.users_service.model.UserInfo;
import com.ust.users_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserInfo registerUser(@RequestBody UserInfo userInfo) {
        return userService.createUser(userInfo);
    }

    @GetMapping("/{email}")
    public UserInfo getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email).orElse(null);
    }

    @GetMapping("/regional")
    public List<RegionalManagerDto> getAllRegionalManagers() {
        return userService.getAllRegionalManagers();
    }

    @GetMapping("/sales")
    public List<StoreManagerDto> getAllSalesManagers() {
        return userService.getAllSalesManagers();
    }
}

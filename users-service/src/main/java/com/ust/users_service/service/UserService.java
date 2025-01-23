package com.ust.users_service.service;

import com.ust.users_service.dto.RegionalManagerDto;
import com.ust.users_service.dto.StoreManagerDto;
import com.ust.users_service.model.UserInfo;
import com.ust.users_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserInfo createUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userRepository.save(userInfo);
    }

    public Optional<UserInfo> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<RegionalManagerDto> getAllRegionalManagers() {
        return userRepository.findAllRegionalManagers();
    }

    public List<StoreManagerDto> getAllSalesManagers() {
        return userRepository.findAllSalesManagers();
    }
}

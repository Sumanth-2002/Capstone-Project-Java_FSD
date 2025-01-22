package com.ust.users_service.repository;

import com.ust.users_service.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByName(String username);
    Optional<UserInfo> findByEmail(String email);
}

package com.ust.users_service.repository;

import com.ust.users_service.dto.RegionalManagerDto;
import com.ust.users_service.dto.StoreManagerDto;
import com.ust.users_service.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByName(String username);
    Optional<UserInfo> findByEmail(String email);
    @Query("SELECT new com.ust.users_service.dto.RegionalManagerDto(u.id, u.name, u.region) " +
            "FROM UserInfo u WHERE u.roles = com.ust.users_service.model.Roles.REGIONAL_MANAGER")
    List<RegionalManagerDto> findAllRegionalManagers();

    @Query("SELECT new com.ust.users_service.dto.StoreManagerDto(u.id, u.name, u.storeId) " +
            "FROM UserInfo u WHERE u.roles = com.ust.users_service.model.Roles.STORE_MANAGER")
    List<StoreManagerDto> findAllSalesManagers();
}

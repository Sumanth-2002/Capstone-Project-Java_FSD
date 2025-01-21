package com.UST.StoresMicroservice.repository;

import com.UST.StoresMicroservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    ResponseEntity<Inventory> findInventoryByStoreId(Long storeId);
}

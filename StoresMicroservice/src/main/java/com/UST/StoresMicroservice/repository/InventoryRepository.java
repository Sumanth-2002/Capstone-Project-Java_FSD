package com.UST.StoresMicroservice.repository;

import com.UST.StoresMicroservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    List<Optional<Inventory>> findInventoryByStoreId(Long storeId);


        @Query("SELECT i FROM Inventory i WHERE i.inventoryId = :inventoryId AND i.productId = :productId")
        Optional<Inventory> findByInventoryIdProductId(@Param("inventoryId") Long inventoryId, @Param("productId") Long productId);
        @Query("SELECT DISTINCT(i.storeId) FROM Inventory i WHERE i.inventoryId = :inventoryId")
        Optional<Long> findStoreIdByInventoryId(@Param("inventoryId") Long inventoryId);

    @Query("SELECT DISTINCT(i.inventoryId) FROM Inventory i WHERE i.storeId = :storeId")
    Optional<Long> findInventoryIdByStoreId(@Param(("storeId")) Long storeId);
}

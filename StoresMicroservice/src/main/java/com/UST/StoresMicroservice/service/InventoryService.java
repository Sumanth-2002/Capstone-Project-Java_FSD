package com.UST.StoresMicroservice.service;

import com.UST.StoresMicroservice.model.Inventory;
import com.UST.StoresMicroservice.model.Store;
import com.UST.StoresMicroservice.repository.InventoryRepository;
import com.UST.StoresMicroservice.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    public Inventory createInventory(Inventory inventory) {

        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryById(Long inventoryId) {
        return inventoryRepository.findById(inventoryId);
    }

    public Optional<Inventory> updateInventory(Long inventoryId, Inventory updatedInventory) {
        return inventoryRepository.findById(inventoryId).map(existingInventory -> {
            existingInventory.setStoreId(updatedInventory.getStoreId());
            existingInventory.setProductId(updatedInventory.getProductId());
            existingInventory.setStock(updatedInventory.getStock());
            existingInventory.setLastUpdated(updatedInventory.getLastUpdated());
            return inventoryRepository.save(existingInventory);
        });
    }

    public boolean deleteInventory(Long inventoryId) {
        if (inventoryRepository.existsById(inventoryId)) {
            inventoryRepository.deleteById(inventoryId);
            return true;
        }
        return false;
    }


    public ResponseEntity<Inventory> getByStoreId(Long storeId) {
        return inventoryRepository.findInventoryByStoreId(storeId);
    }

}

package com.UST.StoresMicroservice.service;

import com.UST.StoresMicroservice.dto.InventoryUpdateDto;
import com.UST.StoresMicroservice.model.Inventory;
import com.UST.StoresMicroservice.repository.InventoryRepository;
import com.UST.StoresMicroservice.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

//    public Optional<Inventory> getInventoryById(Long inventoryId) {
//        return inventoryRepository.findByInventoryIdProductId(inventoryId);
//    }

//    public Optional<Inventory> updateInventory(Long inventoryId, Inventory updatedInventory) {
//        return inventoryRepository.findById(inventoryId).map(existingInventory -> {
//            existingInventory.setStoreId(updatedInventory.getStoreId());
//            existingInventory.setProductId(updatedInventory.getProductId());
//            existingInventory.setStock(updatedInventory.getStock());
//            existingInventory.setLastUpdated(updatedInventory.getLastUpdated());
//            return inventoryRepository.save(existingInventory);
//        });
//    }

    public Optional<Inventory> updatePurchaseInventory(InventoryUpdateDto inventoryUpdateDto) {
        // Find the existing inventory for the given inventoryId and productId
        Optional<Inventory> optionalInventory = inventoryRepository.findByInventoryIdProductId(
                inventoryUpdateDto.getInventoryId(),
                inventoryUpdateDto.getProductId()
        );

        Inventory inventory;
        if (optionalInventory.isPresent()) {
            inventory = optionalInventory.get();
            inventory.setStock(inventory.getStock() + inventoryUpdateDto.getStock());
            inventory.setLastUpdated(LocalDate.now());
        } else {
            inventory = new Inventory(
                    inventoryUpdateDto.getInventoryId(),
                   inventoryRepository.findStoreIdByInventoryId(inventoryUpdateDto.getInventoryId()).get(),
                    inventoryUpdateDto.getProductId(),
                    inventoryUpdateDto.getStock(),
                    LocalDate.now()
            );
        }
        Inventory savedInventory = inventoryRepository.save(inventory);
        return Optional.of(savedInventory);
    }
    public Optional<Inventory> updateSalesInventory(InventoryUpdateDto inventoryUpdateDto) {
        // Find the existing inventory for the given inventoryId and productId
        Optional<Inventory> optionalInventory = inventoryRepository.findByInventoryIdProductId(
                inventoryUpdateDto.getInventoryId(),
                inventoryUpdateDto.getProductId()
        );

        Inventory inventory;
        if (optionalInventory.isPresent()) {
            inventory = optionalInventory.get();
            inventory.setStock(inventory.getStock() - inventoryUpdateDto.getStock());
            inventory.setLastUpdated(LocalDate.now());
        } else {
            inventory = new Inventory(
                    inventoryUpdateDto.getInventoryId(),
                   inventoryRepository.findStoreIdByInventoryId(inventoryUpdateDto.getInventoryId()).get(),
                    inventoryUpdateDto.getProductId(),
                    inventoryUpdateDto.getStock(),
                    LocalDate.now()
            );
        }
        Inventory savedInventory = inventoryRepository.save(inventory);
        return Optional.of(savedInventory);
    }




    public boolean deleteInventory(Long inventoryId) {
        if (inventoryRepository.existsById(inventoryId)) {
            inventoryRepository.deleteById(inventoryId);
            return true;
        }
        return false;
    }


    public List<Optional<Inventory>>getByStoreId(Long storeId) {
        return inventoryRepository.findInventoryByStoreId(storeId);
    }

    public Optional<Long> getInventoryId(Long storeId){
        return inventoryRepository.findInventoryIdByStoreId(storeId);
    }

}
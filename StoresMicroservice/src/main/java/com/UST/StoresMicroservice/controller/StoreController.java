package com.UST.StoresMicroservice.controller;

import com.UST.StoresMicroservice.dto.RegionalStoreId;
import com.UST.StoresMicroservice.dto.StoreDto;
import com.UST.StoresMicroservice.model.Inventory;
import com.UST.StoresMicroservice.model.Store;
import com.UST.StoresMicroservice.repository.StoreRepository;
import com.UST.StoresMicroservice.service.InventoryService;
import com.UST.StoresMicroservice.service.StoreService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private StoreRepository storeRepository;

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        Store createdStore = storeService.createStore(store);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.getAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable("id") Long storeId) {
        return storeService.getStoreById(storeId)
                .map(store -> new ResponseEntity<>(store, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable("id") Long storeId, @RequestBody Store updatedStore) {
        return storeService.updateStore(storeId, updatedStore)
                .map(store -> new ResponseEntity<>(store, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable("id") Long storeId) {
        boolean isDeleted = storeService.deleteStore(storeId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/inventory/create")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory,@RequestParam Long storeId) {
        Inventory createdInventory = inventoryService.createInventory(inventory);
        return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
    }

    @GetMapping("/getStoreByRegion")
    public ResponseEntity<List<Store>> getAllStoresByRegion(@RequestParam String regionName){
        return new ResponseEntity<>(storeService.getAllStoresByRegion(regionName),HttpStatus.OK);
    }

//    @GetMapping("/inventory/getAll")
//    public ResponseEntity<List<Inventory>> getAllInventories() {
//        List<Inventory> inventories = inventoryService.getAllInventories();
//        return new ResponseEntity<>(inventories, HttpStatus.OK);
//    }

    @GetMapping("inventory/getById/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable("id") Long inventoryId) {
        return inventoryService.getInventoryById(inventoryId)
                .map(inventory -> new ResponseEntity<>(inventory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("inventory/update/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable("id") Long inventoryId, @RequestBody Inventory updatedInventory) {
        return inventoryService.updateInventory(inventoryId, updatedInventory)
                .map(inventory -> new ResponseEntity<>(inventory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("inventory/deleteById/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable("id") Long inventoryId) {
        boolean isDeleted = inventoryService.deleteInventory(inventoryId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("inventory/getByStoreId")
    public ResponseEntity<Inventory> getByStoreId(@RequestParam Long storeId){
        return inventoryService.getByStoreId(storeId);
    }

    @GetMapping("/store-ids")
    public List<StoreDto> getStoreIds(String region) {
        return storeService.getStoreIdsAsJson(region);
    }

    @GetMapping("/getALlStores")
    public List<RegionalStoreId>getAllStoreBYRegion(){
        return storeService.getStoreIdsGroupedByRegion();
    }


}

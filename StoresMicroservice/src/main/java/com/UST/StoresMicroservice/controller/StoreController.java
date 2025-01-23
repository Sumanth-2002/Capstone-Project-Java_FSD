package com.UST.StoresMicroservice.controller;

import com.UST.StoresMicroservice.dto.InventoryUpdateDto;
import com.UST.StoresMicroservice.dto.RegionalStoreId;
import com.UST.StoresMicroservice.dto.StoreDto;
import com.UST.StoresMicroservice.model.Inventory;
import com.UST.StoresMicroservice.model.Restock;
import com.UST.StoresMicroservice.model.Store;
import com.UST.StoresMicroservice.repository.StoreRepository;
import com.UST.StoresMicroservice.service.InventoryService;
import com.UST.StoresMicroservice.service.RestockService;
import com.UST.StoresMicroservice.service.StoreService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private RestockService restockService;

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


    @PutMapping("inventory/updatePurchase")
    public ResponseEntity<Optional<Inventory>> updateInventoryPurchase(@RequestBody InventoryUpdateDto inventoryUpdateDto) {
        return ResponseEntity.ok(inventoryService.updatePurchaseInventory(inventoryUpdateDto));
    }
      @PutMapping("inventory/updateSale")
    public ResponseEntity<Optional<Inventory>> updateInventorySale(@RequestBody InventoryUpdateDto inventoryUpdateDto) {
        return ResponseEntity.ok(inventoryService.updateSalesInventory(inventoryUpdateDto));
    }



    @DeleteMapping("inventory/deleteById/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable("id") Long inventoryId) {
        boolean isDeleted = inventoryService.deleteInventory(inventoryId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("inventory/getByStoreId")
    public List<Optional<Inventory>> getByStoreId(@RequestParam Long storeId){
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

    @GetMapping("/getInventoryId/{storeId}")
    public Optional<Long> getInventoryId(@PathVariable Long storeId){
        return  inventoryService.getInventoryId(storeId);
    }
    @PostMapping("/raise-restock-request")
    public ResponseEntity<Restock> raiseRequest(Restock restock){
        return ResponseEntity.ok(restockService.requestForStock(restock));
    }
    @GetMapping("/get-requests")
    public ResponseEntity<List<Restock>> getAllRequests(){
        return ResponseEntity.ok(restockService.getAllRestock());
    }

    @PutMapping("/update-requests")
    public  ResponseEntity<Restock> updateRestock(Restock restock){
        return ResponseEntity.ok(restockService.updateStatus(restock));
    }

    @GetMapping("/get-pending-requests")
    public ResponseEntity<List<Restock>> getPendingRequests(){
        return  ResponseEntity.ok(restockService.getAllPendingRequests());
    }

}
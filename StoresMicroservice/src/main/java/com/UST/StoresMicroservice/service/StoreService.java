package com.UST.StoresMicroservice.service;

import com.UST.StoresMicroservice.model.Store;
import com.UST.StoresMicroservice.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Optional<Store> getStoreById(Long storeId) {
        return storeRepository.findById(storeId);
    }

    public Optional<Store> updateStore(Long storeId, Store updatedStore) {
        return storeRepository.findById(storeId).map(existingStore -> {
            existingStore.setStoreName(updatedStore.getStoreName());
            existingStore.setRegion(updatedStore.getRegion());
            existingStore.setAddress(updatedStore.getAddress());
            return storeRepository.save(existingStore);
        });
    }

    public boolean deleteStore(Long storeId) {
        if (storeRepository.existsById(storeId)) {
            storeRepository.deleteById(storeId);
            return true;
        }
        return false;
    }

    public List<Store> getAllStoresByRegion(String regionName) {
        return storeRepository.findAllStoreByRegion(regionName);
    }

    public String getStoreIdsAsJson(String region) {
        // Fetch only storeId values

        List<Integer> storeIds = storeRepository.findStoreIdsByRegion(region);

        // Convert to JSON format
        return storeIds.stream()
                .map(storeId -> String.format("{\"storeId\": %d}", storeId))
                .collect(Collectors.joining(", ", "[", "]"));
    }
}

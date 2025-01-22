package com.UST.StoresMicroservice.service;

import com.UST.StoresMicroservice.dto.RegionalStoreId;
import com.UST.StoresMicroservice.dto.StoreDto;
import com.UST.StoresMicroservice.model.Store;
import com.UST.StoresMicroservice.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.JobKOctets;
import java.util.List;
import java.util.Map;
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

    public List<StoreDto> getStoreIdsAsJson(String region) {
        List<StoreDto> storeIds = storeRepository.findStoreIdsByRegion(region);
        return storeIds;
    }

    public List<RegionalStoreId> getStoreIdsGroupedByRegion() {
        List<Object[]> results = storeRepository.findRegionAndStoreIds();
        Map<String, List<Long>> groupedData = results.stream()
                .collect(Collectors.groupingBy(
                        row -> (String) row[0], // Group by region
                        Collectors.mapping(row -> (Long) row[1], Collectors.toList()) // Map store IDs to a list
                ));
        return groupedData.entrySet().stream()
                .map(entry -> new RegionalStoreId(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }


}

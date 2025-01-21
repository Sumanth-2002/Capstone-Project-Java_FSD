package com.UST.StoresMicroservice.repository;

import com.UST.StoresMicroservice.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    List<Store> findAllStoreByRegion(String regionName);
    @Query("SELECT s.storeId FROM Store s WHERE s.region = :region")
    List<Integer> findStoreIdsByRegion(@Param("region") String region);

}

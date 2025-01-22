package com.UST.StoresMicroservice.repository;

import com.UST.StoresMicroservice.dto.RegionalStoreId;
import com.UST.StoresMicroservice.dto.StoreDto;
import com.UST.StoresMicroservice.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    List<Store> findAllStoreByRegion(String regionName);
    @Query("SELECT new com.UST.StoresMicroservice.dto.StoreDto(s.storeName, s.storeId) FROM Store s WHERE s.region = :region")
    List<StoreDto> findStoreIdsByRegion(@Param("region") String region);

    //
//    @Query("SELECT com.UST.StoresMicroservice.dto.RegionalStoreId(s.region, s.storeId FROM Store s ORDER BY s.region")
//    List<RegionalStoreId> findStoreIdGroupByRegion();
@Query("SELECT s.region, s.storeId FROM Store s ORDER BY s.region")
List<Object[]> findRegionAndStoreIds();


}

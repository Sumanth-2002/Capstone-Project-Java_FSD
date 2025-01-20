package com.ust.Purchase_Service.repository;

import com.ust.Purchase_Service.model.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchases,Long> {
    @Query(value = "SELECT SUM(p.totalCost) FROM Purchases p WHERE p.purchaseDate BETWEEN :startDate AND :endDate", nativeQuery = true)
    Double getPurchaseDataByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = """
                SELECT YEAR(p.purchaseDate) AS year, SUM(p.totalCost) AS totalCost
                FROM Purchases p
                GROUP BY YEAR(p.purchaseDate)
                ORDER BY year desc
            """, nativeQuery = true)
    List<Object[]> getTotalCostByYear();

    @Query(value = """
                SELECT MONTH(p.purchaseDate) AS month, SUM(p.totalCost) AS totalCost
                FROM Purchases p
                GROUP BY MONTH(p.purchaseDate)
                ORDER BY month 
            """, nativeQuery = true)
    List<Object[]> getTotalCostByMonth();


    @Query(value = """
                SELECT SUM(p.totalCost)
                FROM Purchases p
                WHERE p.storeId IN :storeIds
            """, nativeQuery = true)
    Double getPurchaseDataForRegion(@Param("storeIds") List<Integer> storeId);


    @Query(value = """
                SELECT YEAR(p.purchaseDate) AS year, SUM(p.totalCost) AS totalCost
                FROM Purchases p
                WHERE p.storeId IN :storeIds
                GROUP BY YEAR(p.purchaseDate)
                ORDER BY year DESC
            """, nativeQuery = true)
    List<Object[]> getPurchaseDataForRegionByYear(@Param("storeIds") List<Integer> storeId);
}

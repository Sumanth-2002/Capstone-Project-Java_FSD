package com.ust.sales_service.repository;

import com.ust.sales_service.dto.SalesSummaryDto;
import com.ust.sales_service.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
    @Query(value = "SELECT s.sale_date, SUM(s.total_price), COUNT(s.sale_id) " +
            "FROM sales s GROUP BY s.sale_date", nativeQuery = true)
    List<Object[] > getSalesSummaryByDate();
    @Query(value = """
                SELECT SUM(p.total_price)
                FROM Sales p
                WHERE p.sale_id IN :storeIds
            """, nativeQuery = true)
    Double getTotalSaleDataRegion(@Param("storeIds") List<Long> storeId);
    @Query(value = """
                SELECT SUM(p.total_price)
                FROM Sales p
                WHERE p.store_id = :storeId
            """, nativeQuery = true)
    Double getStoreSaleData(@Param("storeId") Integer storeId);

    @Query(value = """
        SELECT COUNT(s.product_id) AS productCount, p.name 
        FROM SALES s 
        JOIN Product p ON p.product_id = s.product_id 
        WHERE YEAR(s.sale_date) = :year 
        GROUP BY p.name
        """, nativeQuery = true)
    List<Object[]> getProductMetric(@Param("year") Integer year);
    @Query(value = """
        SELECT COUNT(s.product_id) AS productCount, p.name 
        FROM SALES s 
        JOIN Product p ON p.product_id = s.product_id 
        WHERE MONTH(s.sale_date) = :month AND YEAR(s.sale_date) = :year 
        GROUP BY p.name
        """, nativeQuery = true)
    List<Object[]> getProductMetricByMonthAndYear(@Param("month") Integer month, @Param("year") Integer year);

    @Query(value = """
           SELECT MONTH(s.sale_date) as month, COUNT(DISTINCT(s.customer_id)) as customers 
           FROM Sales s 
           WHERE YEAR(s.sale_date) = :year 
           GROUP BY MONTH(s.sale_date)
           ORDER BY MONTH(s.sale_date)
           """, nativeQuery = true)
    public List<Long[]> getCustomersPerMonth(@Param("year") int year);

    @Query(value = """
            SELECT MONTH(s.sale_date) as month, sum(s.total_price) 
            FROM Sales s 
            WHERE YEAR(s.sale_date)= :year
            GROUP BY MONTH(s.sale_date)
            ORDER BY MONTH(s.sale_date)""",nativeQuery = true)
    public List<Double[]> getSaleDataMonthly(@Param("year") int year);


}

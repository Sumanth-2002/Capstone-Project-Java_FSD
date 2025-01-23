package com.ust.sales_service.repository;

import com.ust.sales_service.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
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
    Double getStoreSaleData(@Param("storeId") Long storeId);

    @Query(value = """
           SELECT MONTH(s.sale_date) as month, COUNT(DISTINCT(s.customer_id)) as customers 
           FROM Sales s 
           WHERE YEAR(s.sale_date) = :year 
           GROUP BY MONTH(s.sale_date)
           ORDER BY MONTH(s.sale_date)
           """, nativeQuery = true)
    public List<Object[]> getCustomersPerMonth(@Param("year") int year);

    @Query(value = """
            SELECT MONTH(s.sale_date) as month, sum(s.total_price) 
            FROM Sales s 
            WHERE YEAR(s.sale_date)= :year
            GROUP BY MONTH(s.sale_date)
            ORDER BY MONTH(s.sale_date)""",nativeQuery = true)
    public List<Object[]> getSaleDataMonthly(@Param("year") int year);

    @Query(value = """
        SELECT MONTH(s.sale_date) as month, SUM(s.quantity) as productQuantity 
        FROM Sales s
        JOIN Product p ON s.product_id = p.product_id 
        WHERE p.name = :productName AND YEAR(s.sale_date) = :year
        GROUP BY MONTH(s.sale_date)
        ORDER BY MONTH(s.sale_date)
        """, nativeQuery = true)
    List<Object[]> getMonthlyProductQuantity(
            @Param("productName") String productName,
            @Param("year") int year);

}

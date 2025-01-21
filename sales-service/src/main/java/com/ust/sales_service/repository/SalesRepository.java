package com.ust.sales_service.repository;

import com.ust.sales_service.dto.SalesSummaryDto;
import com.ust.sales_service.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
    @Query(value = "SELECT s.sale_date, SUM(s.total_price), COUNT(s.sale_id) " +
            "FROM sales s GROUP BY s.sale_date", nativeQuery = true)
    List<Object[] > getSalesSummaryByDate();

}

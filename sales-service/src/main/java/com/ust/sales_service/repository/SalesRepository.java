package com.ust.sales_service.repository;

import com.ust.sales_service.dto.SalesSummaryDto;
import com.ust.sales_service.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {

    @Query("SELECT com.ust.sales_service.dto.SalesSummaryDto(s.saleDate, SUM(s.totalPrice), COUNT(s.saleId)) " +
            "FROM Sales s GROUP BY s.saleDate")
    List<SalesSummaryDto> getSalesSummaryByDate();
}

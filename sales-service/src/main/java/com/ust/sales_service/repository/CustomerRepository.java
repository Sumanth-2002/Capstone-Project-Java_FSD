package com.ust.sales_service.repository;

import com.ust.sales_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(
            value = "SELECT sum(s.quantity) AS quantity, s.product_id AS productId, c.name AS customerName " +
                    "FROM Customer c " +
                    "JOIN Sales s ON c.customer_id = s.customer_id " +
                    "WHERE c.customer_id = :customerId " +
                    "GROUP BY s.product_id, c.name order by quantity desc ",
            nativeQuery = true
    )
    List<Object[]> getCustomerDataById(@Param("customerId") Long customerId);


}

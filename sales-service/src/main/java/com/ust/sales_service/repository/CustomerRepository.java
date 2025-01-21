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

    @Query(value = "SELECT COUNT(s.product_id),s.product_id, c.name FROM Customer c JOIN Sales s ON c.customer_id = s.customer_id GROUP BY s.product_id, c.name", nativeQuery = true)
    List<Object[]> getCustomerDataById();

}

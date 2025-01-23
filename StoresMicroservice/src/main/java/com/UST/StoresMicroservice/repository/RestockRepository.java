package com.UST.StoresMicroservice.repository;

import com.UST.StoresMicroservice.model.Restock;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestockRepository extends JpaRepository<Restock,Long> {
    @Query("SELECT r FROM Restock r WHERE r.status = com.UST.StoresMicroservice.model.Status.Requested")
    List<Restock> getPendingRequests();
}

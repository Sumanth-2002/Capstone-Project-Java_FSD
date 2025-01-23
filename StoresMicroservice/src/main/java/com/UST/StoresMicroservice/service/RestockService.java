package com.UST.StoresMicroservice.service;

import com.UST.StoresMicroservice.model.Restock;
import com.UST.StoresMicroservice.repository.RestockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestockService {
    @Autowired
    private RestockRepository restockRepository;

    public Restock requestForStock(Restock restock){
        return restockRepository.save(restock);
    }
    public List<Restock> getAllRestock() {
        return restockRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Restock::getStatus)) // Sort by status in reverse order
                .collect(Collectors.toList()); // Collect the sorted stream into a list
    }
    public Restock updateStatus(Restock restock){
        Restock exRestock = restockRepository.findById(restock.getRequestId()).get();
        exRestock.setStatus(restock.getStatus());
        return restockRepository.save(restock);
    }

    public List<Restock> getAllPendingRequests(){
        return  restockRepository.getPendingRequests();
    }


}

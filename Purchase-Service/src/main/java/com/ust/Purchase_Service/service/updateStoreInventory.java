package com.ust.Purchase_Service.service;

import com.ust.Purchase_Service.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class updateStoreInventory {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;


}
